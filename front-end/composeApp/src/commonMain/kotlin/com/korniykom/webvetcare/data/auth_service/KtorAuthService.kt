package com.korniykom.webvetcare.data.auth_service

import com.korniykom.webvetcare.data.dto.LoginRequestSerializable
import com.korniykom.webvetcare.data.dto.LoginResponseSerializable
import com.korniykom.webvetcare.data.dto.RegisterRequestSerializable
import com.korniykom.webvetcare.data.dto.RegisterResponseSerializable
import com.korniykom.webvetcare.data.networking.post
import com.korniykom.webvetcare.domain.auth_service.AuthService
import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.NetworkResult
import com.korniykom.webvetcare.domain.util.TokenStorage
import io.ktor.client.*

class KtorAuthService(
    private val httpClient: HttpClient,
    private val tokenStorage: TokenStorage
) : AuthService {
    override suspend fun register(
        email: String,
        password: String,
        username: String,
    ): NetworkResult<RegisterResponseSerializable, DataError.Remote> {
        return httpClient.post<RegisterRequestSerializable, RegisterResponseSerializable>(
            route = "/auth/register",
            body = RegisterRequestSerializable(
                email = email,
                password = password,
                username = username
            )
        )
    }

    override suspend fun login(
        email: String,
        password: String
    ): NetworkResult<LoginResponseSerializable, DataError.Remote> {
        return httpClient.post<LoginRequestSerializable, LoginResponseSerializable>(
            route = "/auth/login",
            body = LoginRequestSerializable(
                email = email,
                password = password
            )
        )
    }
}