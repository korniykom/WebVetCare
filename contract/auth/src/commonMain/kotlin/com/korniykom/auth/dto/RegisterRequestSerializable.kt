package com.korniykom.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestSerializable(
    val email: String,
    val password: String,
    val username: String,
)