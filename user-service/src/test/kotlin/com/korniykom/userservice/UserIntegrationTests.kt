package com.korniykom.userservice

import com.korniykom.userservice.dto.request.BecomeDoctorRequest
import com.korniykom.userservice.dto.request.BecomePatientRequest
import com.korniykom.userservice.repository.DoctorProfileRepository
import com.korniykom.userservice.repository.PatientProfileRepository
import com.korniykom.userservice.repository.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MediaType
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.boot.web.server.context.WebServerApplicationContext
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.Jwt.withTokenValue
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.Instant

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class UserIntegrationTests {

    @MockitoBean
    lateinit var jwtDecoder: JwtDecoder

    private fun mockJwt(userId: String = "test-user-id", email: String = "test@test.com"): Jwt {
        val jwt = withTokenValue("test-token")
            .header("alg", "RS256")
            .subject(userId)
            .claim("email", email)
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(3600))
            .build()
        `when`(jwtDecoder.decode("test-token")).thenReturn(jwt)
        return jwt
    }

    companion object {
        @Container
        @JvmStatic
        val postgres = PostgreSQLContainer<Nothing>("postgres:16").apply {
            withDatabaseName("userdb")
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
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var doctorProfileRepository: DoctorProfileRepository

    @Autowired
    lateinit var patientProfileRepository: PatientProfileRepository

    @Autowired
    lateinit var webServerApplicationContext: WebServerApplicationContext

    lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setup() {
        mockJwt()

        webTestClient = WebTestClient.bindToServer()
            .baseUrl("http://localhost:${webServerApplicationContext.webServer!!.port}")
            .build()

        patientProfileRepository.deleteAll()
        doctorProfileRepository.deleteAll()
        userRepository.deleteAll()

        webTestClient.get()
            .uri("/api/users/me")
            .headers { it.setBearerAuth("test-token") }
            .exchange()
            .expectStatus().isOk


    }

    @Test
    fun `GET me without token returns 401`() {
        webTestClient.get()
            .uri("/api/users/me")
            .exchange()
            .expectStatus().isUnauthorized
    }

    @Test
    fun `GET me with token returns 200`() {
        webTestClient.get()
            .uri("/api/users/me")
            .headers { it.setBearerAuth("test-token") }
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.email").isEqualTo("test@test.com")
            .jsonPath("$.roles").isArray
    }

    @Test
    fun `POST become-doctor without token returns 401`() {
        webTestClient.post()
            .uri("/api/users/become-doctor")
            .bodyValue(BecomeDoctorRequest(specialization = "Surgery"))
            .exchange()
            .expectStatus().isUnauthorized
    }

    @Test
    fun `POST become-doctor with token returns 200`() {
        webTestClient.post()
            .uri("/api/users/become-doctor")
            .headers{ it.setBearerAuth("test-token")}
            .bodyValue(BecomeDoctorRequest(specialization = "Surgery"))
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.specialization").isEqualTo("Surgery")

        webTestClient.get()
            .uri("/api/users/me")
            .headers{ it.setBearerAuth("test-token")}
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.roles").isArray
            .jsonPath("$.roles[?(@ == 'DOCTOR')]").exists()
    }

    @Test
    fun `POST become-patient with token returns 200`() {
        webTestClient.post()
            .uri("/api/users/become-patient")
            .headers{ it.setBearerAuth("test-token")}
            .bodyValue(BecomePatientRequest(contact_email = "mail@mail.com"))
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.contactEmail").isEqualTo("mail@mail.com")

        webTestClient.get()
            .uri("/api/users/me")
            .headers{ it.setBearerAuth("test-token")}
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.roles").isArray
            .jsonPath("$.roles[?(@ == 'PATIENT')]").exists()
    }


    @Test
    fun `POST become-patient without token returns 401`() {
        webTestClient.post()
            .uri("/api/users/become-patient")
            .bodyValue(BecomePatientRequest(contact_email = "example@email.com"))
            .exchange()
            .expectStatus().isUnauthorized
    }

    @Test
    fun `GET doctor-profile without token returns 401`() {
        webTestClient.get()
            .uri("/api/users/doctor-profile")
            .exchange()
            .expectStatus().isUnauthorized
    }

    @Test
    fun `GET patient-profile without token returns 401`() {
        webTestClient.get()
            .uri("/api/users/patient-profile")
            .exchange()
            .expectStatus().isUnauthorized
    }

    @Test
    fun `GET doctor-profile with token returns 200`() {
        webTestClient.post()
            .uri("/api/users/become-doctor")
            .headers { it.setBearerAuth("test-token") }
            .bodyValue(BecomeDoctorRequest(specialization = "Surgery"))
            .exchange()
            .expectStatus().isOk

        webTestClient.get()
            .uri("/api/users/doctor-profile")
            .headers{ it.setBearerAuth("test-token")}
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `GET patient-profile with token returns 200`() {
        webTestClient.post()
            .uri("/api/users/become-patient")
            .headers { it.setBearerAuth("test-token") }
            .bodyValue(BecomePatientRequest(contact_email = "mail@mail.com"))
            .exchange()
            .expectStatus().isOk


        webTestClient.get()
            .uri("/api/users/patient-profile")
            .headers{ it.setBearerAuth("test-token")}
            .exchange()
            .expectStatus().isOk
    }
}