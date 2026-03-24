package com.korniykom.auth.mappers

import com.korniykom.auth.domain.type.User
import com.korniykom.auth.entity.UserEntity

fun UserEntity.toUser(): User {
    return User(
        id = id!!,
        username = username,
        email = email,
    )
}