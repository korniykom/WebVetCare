package com.korniykom.auth.service

import com.korniykom.auth.dto.LoginRequest
import com.korniykom.auth.dto.RegisterRequest
import com.korniykom.auth.dto.TokenResponse
import org.springframework.stereotype.Service

@Service
class AuthService {

    fun register(request: RegisterRequest): TokenResponse {
        TODO("implement")
    }

    fun login(request: LoginRequest): Pair<TokenResponse, String> {
        TODO("implement")
    }

    fun refresh(refreshToken: String): Pair<TokenResponse, String> {
        TODO("implement")
    }

    fun logout() {
        TODO("implement")
    }
}