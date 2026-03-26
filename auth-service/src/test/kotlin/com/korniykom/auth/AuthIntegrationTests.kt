package com.korniykom.auth

import com.korniykom.auth.dto.RegisterRequest
import com.korniykom.auth.repository.AuthUserRepository
import com.korniykom.auth.repository.RefreshTokenRepository
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
}