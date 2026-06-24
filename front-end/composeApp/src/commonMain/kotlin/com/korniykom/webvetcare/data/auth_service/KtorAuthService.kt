package com.korniykom.webvetcare.data.auth_service

import com.korniykom.auth.dto.LoginRequestSerializable
import com.korniykom.webvetcare.data.dto.LoginResponseSerializable
import com.korniykom.webvetcare.data.dto.RegisterRequestSerializable
import com.korniykom.webvetcare.data.dto.RegisterResponseSerializable
import com.korniykom.webvetcare.data.networking.post
import com.korniykom.webvetcare.domain.auth_service.AuthService
import com.korniykom.webvetcare.domain.auth_service.LoginResponse
import com.korniykom.webvetcare.domain.auth_service.RegisterResponse
import com.korniykom.webvetcare.domain.mappers.toDomain
import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.NetworkResult
import com.korniykom.webvetcare.domain.util.TokenStorage
import com.korniykom.webvetcare.domain.util.map
import io.ktor.client.HttpClient

class KtorAuthService(
    private val httpClient: HttpClient,
    private val tokenStorage: TokenStorage
) : AuthService {
    override suspend fun register(
        email: String,
        password: String,
        username: String,
    ): NetworkResult<RegisterResponse, DataError.Remote> {
        return httpClient.post<RegisterRequestSerializable, RegisterResponseSerializable>(
            route = "/auth/register",
            body = RegisterRequestSerializable(
                email = email,
                password = password,
                username = username
            )
        ).map { response ->
            tokenStorage.saveAccessToken(response.accessToken)
            response.toDomain()
        }

    }

    override suspend fun login(
        email: String,
        password: String
    ): NetworkResult<LoginResponse, DataError.Remote> {
        return httpClient.post<LoginRequestSerializable, LoginResponseSerializable>(
            route = "/auth/login",
            body = LoginRequestSerializable(
                email = email,
                password = password
            )
        ).map { response ->
            tokenStorage.saveAccessToken(response.accessToken)
            response.toDomain()
        }
    }
}