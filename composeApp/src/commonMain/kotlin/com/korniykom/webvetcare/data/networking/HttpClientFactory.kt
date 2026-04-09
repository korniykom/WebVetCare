package com.korniykom.webvetcare.data.networking

import com.korniykom.webvetcare.data.dto.AuthResponse
import com.korniykom.webvetcare.domain.logging.WebVetCareLogger
import com.korniykom.webvetcare.domain.util.NetworkResult
import com.korniykom.webvetcare.domain.util.TokenStorage
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class HttpClientFactory(
    private val webVetCareLogger: WebVetCareLogger,
    private val tokenStorage: TokenStorage
) {
    fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        webVetCareLogger.debug(message)
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val access = tokenStorage.getAccessToken()
                        if (access != null) BearerTokens(access, "") else null
                    }

                    refreshTokens {
                        val refreshClient = HttpClient(engine) {
                            install(ContentNegotiation) {
                                json(Json { ignoreUnknownKeys = true })
                            }
                        }

                        val httpResponse = refreshClient.post {
                            url(constructRoute("/api/auth/refresh"))
                            contentType(ContentType.Application.Json)
                        }

                        val result = responseToResult<AuthResponse>(httpResponse)

                        when (result) {
                            is NetworkResult.Success -> {
                                tokenStorage.saveAccessToken(result.data.accessToken)
                                BearerTokens(result.data.accessToken, "")
                            }

                            is NetworkResult.Failure -> {
                                tokenStorage.clearToken()
                                null
                            }
                        }
                    }

                    sendWithoutRequest { request ->
                        !request.url.toString()
                            .contains("/api/auth/login") && !request.url.toString()
                            .contains("/api/auth/register")
                    }
                }
            }
        }
    }
}