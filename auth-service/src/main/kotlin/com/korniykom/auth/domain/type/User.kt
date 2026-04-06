package com.korniykom.auth.domain.type

import java.util.*

typealias UserId = UUID

data class User(
    val id: UserId,
    val email: String,
)