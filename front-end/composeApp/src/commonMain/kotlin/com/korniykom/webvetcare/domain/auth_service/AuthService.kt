package com.korniykom.webvetcare.domain.auth_service

import com.korniykom.webvetcare.data.dto.LoginResponseSerializable
import com.korniykom.webvetcare.data.dto.RegisterResponseSerializable
import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.NetworkResult

interface AuthService {
    suspend fun register(
        email: String,
        password: String,
        username: String
    ) : NetworkResult<RegisterResponseSerializable,DataError.Remote>

    suspend fun login(
        email: String,
        password: String,
    ): NetworkResult<LoginResponseSerializable, DataError.Remote>
}