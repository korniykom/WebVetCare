package com.korniykom.webvetcare.domain.mappers

import com.korniykom.webvetcare.data.dto.RegisterRequestSerializable
import com.korniykom.webvetcare.data.dto.RegisterResponseSerializable
import com.korniykom.webvetcare.domain.auth.RegisterRequest
import com.korniykom.webvetcare.domain.auth.RegisterResponse

fun RegisterResponseSerializable.toDomain(): RegisterResponse {
    return RegisterResponse(
        accessToken = accessToken
    )
}

fun RegisterRequestSerializable.toDomain(): RegisterRequest {
    return RegisterRequest(
        email = email,
        password = password
    )
}