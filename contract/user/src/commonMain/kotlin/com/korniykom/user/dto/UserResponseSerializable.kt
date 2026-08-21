package com.korniykom.user.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val email: String,
    val roles: Set<Role>,
    val username: String,
)