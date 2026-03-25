package com.korniykom.auth.domain.type

import java.util.UUID

typealias UserId = UUID

data class User(
    val id: UserId,
    val email: String,
)