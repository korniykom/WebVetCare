package com.korniykom.webvetcare.domain.auth

import com.korniykom.webvetcare.data.dto.RegisterResponseSerializable
import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.NetworkResult

interface AuthService {
    suspend fun register(
        email: String,
        password: String,
    ) : NetworkResult<RegisterResponse,DataError.Remote>

    suspend fun login(
        email: String,
        password: String,
    ): NetworkResult<LoginResponse, DataError.Remote>
}