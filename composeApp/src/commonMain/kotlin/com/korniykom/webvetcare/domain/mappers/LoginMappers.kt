package com.korniykom.webvetcare.domain.mappers

import com.korniykom.webvetcare.data.dto.LoginRequestSerializable
import com.korniykom.webvetcare.data.dto.LoginResponseSerializable
import com.korniykom.webvetcare.domain.auth_service.LoginRequest
import com.korniykom.webvetcare.domain.auth_service.LoginResponse

fun LoginRequestSerializable.toDomain(): LoginRequest {
    return LoginRequest(
        email = email,
        password = password,
    )
}

fun LoginResponseSerializable.toDomain(): LoginResponse {
    return LoginResponse(
        accessToken = accessToken
    )
}