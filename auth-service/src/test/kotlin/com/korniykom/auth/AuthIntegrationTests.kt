package com.korniykom.auth

import com.korniykom.auth.dto.LoginRequest
import com.korniykom.auth.dto.RegisterRequest
import com.korniykom.auth.repository.AuthUserRepository
import com.korniykom.auth.repository.RefreshTokenRepository
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.context.WebServerApplicationContext
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class AuthIntegrationTests {

    companion object {
        @Container
        @JvmStatic
        val postgres = PostgreSQLContainer<Nothing>("postgres:16").apply {
            withDatabaseName("authdb")
            withUsername("postgres")
            withPassword("postgres")
        }

        @DynamicPropertySource
        @JvmStatic
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
        }
    }


    @Autowired
    lateinit var webServerApplicationContext: WebServerApplicationContext

    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var authUserRepository: AuthUserRepository

    @Autowired
    lateinit var refreshTokenRepository: RefreshTokenRepository

    @BeforeEach
    fun setup() {
        webTestClient = WebTestClient.bindToServer()
            .baseUrl("http://localhost:${webServerApplicationContext.webServer!!.port}")
            .build()
    }

    @BeforeEach
    fun cleanup() {
        refreshTokenRepository.deleteAll()
        authUserRepository.deleteAll()
    }

    @Test
    fun `register success`() {
        webTestClient.post()
            .uri("/api/auth/register")
            .bodyValue(RegisterRequest("test@example.com", password = "password"))
            .exchange()
            .expectStatus().isCreated
            .expectHeader().exists("Set-Cookie")
            .expectBody()
            .jsonPath("$.accessToken").isNotEmpty
    }

    @Test
    fun `register duplicate email`() {
        webTestClient.post()
            .uri("/api/auth/register")
            .bodyValue(RegisterRequest("test@example.com", password = "password"))
            .exchange()
        webTestClient.post()
            .uri("/api/auth/register")
            .bodyValue(RegisterRequest("test@example.com", password = "password"))
            .exchange()
            .expectStatus().is4xxClientError
    }

    @Test
    fun `register invalid email`() {
        webTestClient.post()
            .uri("/api/auth/register")
            .bodyValue(RegisterRequest("goofy-mail", password = "password"))
            .exchange()
            .expectStatus().is4xxClientError
    }

    @Test
    fun `login success`() {
        webTestClient.post()
            .uri("/api/auth/register")
            .bodyValue(RegisterRequest("test@example.com", password = "password"))
            .exchange()

        webTestClient.post()
            .uri("/api/auth/login")
            .bodyValue(LoginRequest(email = "test@example.com", password = "password"))
            .exchange()
            .expectStatus().isOk
            .expectHeader().exists("Set-Cookie")
            .expectBody()
            .jsonPath("$.accessToken").isNotEmpty
    }

    @Test
    fun `login wrong password`() {
        webTestClient.post()
            .uri("/api/auth/register")
            .bodyValue(RegisterRequest("test@example.com", password = "password"))
            .exchange()

        webTestClient.post()
            .uri("/api/auth/login")
            .bodyValue(LoginRequest(email = "test@example.com", password = "password123"))
            .exchange()
            .expectStatus().is4xxClientError
    }

    @Test
    fun `login user not found`() {
        webTestClient.post()
            .uri("/api/auth/register")
            .bodyValue(RegisterRequest("test@example.com", password = "password"))
            .exchange()

        webTestClient.post()
            .uri("/api/auth/login")
            .bodyValue(LoginRequest(email = "test1@example.com", password = "password"))
            .exchange()
            .expectStatus().is4xxClientError

    }

    @Test
    fun `refresh success`() {
        val registerResult = webTestClient.post()
            .uri("/api/auth/register")
            .bodyValue(RegisterRequest("test@example.com", password = "password"))
            .exchange()
            .returnResult()

        val refreshCookie = registerResult.responseHeaders["Set-Cookie"]
            ?.find { it.startsWith("refresh_token") }
            ?: fail("No refresh cookie")

        val refreshTokenValue = refreshCookie.substringAfter("refresh_token=").substringBefore(";")

        webTestClient.post()
            .uri("/api/auth/refresh")
            .cookie("refresh_token", refreshTokenValue)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.accessToken").isNotEmpty
    }

    @Test
    fun `refresh invalid token`() {
        webTestClient.post()
            .uri("/api/auth/refresh")
            .cookie("refresh_token", "diy_token")
            .exchange()
            .expectStatus().isUnauthorized
    }

    @Test
    fun `logout success`() {
        val registerResult = webTestClient.post()
            .uri("/api/auth/register")
            .bodyValue(RegisterRequest("test@example.com", password = "password"))
            .exchange()
            .returnResult()

        val refreshCookie = registerResult.responseHeaders["Set-Cookie"]
            ?.find { it.startsWith("refresh_token") }
            ?: fail("No refresh cookie")

        val refreshTokenValue = refreshCookie.substringAfter("refresh_token=").substringBefore(";")

        webTestClient.post()
            .uri("/api/auth/refresh")
            .cookie("refresh_token", refreshTokenValue)
            .exchange()
            .expectStatus().isOk

        webTestClient.post()
            .uri("/api/auth/refresh")
            .cookie("refresh_token", refreshTokenValue)
            .exchange()
            .expectStatus().isUnauthorized

    }

    @Test
    fun `GET jwks returns 200 and key`() {
        webTestClient.get()
            .uri("/.well-known/jwks.json")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.keys[0].kid").isEqualTo("auth-service-key")
            .jsonPath("$.keys[0].alg").isEqualTo("RS256")
            .jsonPath("$.keys[0].use").isEqualTo("sig")
            .jsonPath("$.keys[0].kty").isEqualTo("RSA")
    }
}