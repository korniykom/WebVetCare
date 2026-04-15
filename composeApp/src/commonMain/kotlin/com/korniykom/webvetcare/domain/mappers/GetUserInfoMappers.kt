package com.korniykom.webvetcare.domain.mappers

import com.korniykom.webvetcare.data.dto.GetUserResponseInfoSerializable
import com.korniykom.webvetcare.domain.user_service.GetUserInfoResponse

fun GetUserResponseInfoSerializable.toDomain(): GetUserInfoResponse {
    return GetUserInfoResponse(
        id = id,
        email = email,
        roles = roles
    )
}