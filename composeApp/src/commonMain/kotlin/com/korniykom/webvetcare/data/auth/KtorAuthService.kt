package com.korniykom.webvetcare.data.auth

import com.korniykom.webvetcare.data.dto.LoginRequestSerializable
import com.korniykom.webvetcare.data.dto.LoginResponseSerializable
import com.korniykom.webvetcare.data.dto.RegisterRequestSerializable
import com.korniykom.webvetcare.data.dto.RegisterResponseSerializable
import com.korniykom.webvetcare.data.networking.post
import com.korniykom.webvetcare.domain.auth.AuthService
import com.korniykom.webvetcare.domain.auth.LoginResponse
import com.korniykom.webvetcare.domain.auth.RegisterResponse
import com.korniykom.webvetcare.domain.mappers.toDomain
import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.NetworkResult
import com.korniykom.webvetcare.domain.util.map
import io.ktor.client.HttpClient

class KtorAuthService(
    private val httpClient: HttpClient
) : AuthService {
    override suspend fun register(
        email: String,
        password: String
    ): NetworkResult<RegisterResponse, DataError.Remote> {
        return httpClient.post<RegisterRequestSerializable, RegisterResponseSerializable>(
            route = "/auth/register",
            body = RegisterRequestSerializable(
                email = email,
                password = password
            )
        ).map { registerResponseSerializable ->
            registerResponseSerializable.toDomain()
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
        ).map { loginResponseSerializable ->
            loginResponseSerializable.toDomain()
        }
    }
}