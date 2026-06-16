package com.korniykom.webvetcare.domain.mappers

import com.korniykom.webvetcare.data.dto.RegisterRequestSerializable
import com.korniykom.webvetcare.data.dto.RegisterResponseSerializable
import com.korniykom.webvetcare.domain.auth_service.RegisterRequest
import com.korniykom.webvetcare.domain.auth_service.RegisterResponse

fun RegisterResponseSerializable.toDomain(): RegisterResponse {
    return RegisterResponse(
        accessToken = accessToken
    )
}

fun RegisterRequestSerializable.toDomain(): RegisterRequest {
    return RegisterRequest(
        email = email,
        password = password,
        username = username
    )
}