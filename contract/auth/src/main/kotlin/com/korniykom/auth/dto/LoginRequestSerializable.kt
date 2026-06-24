package com.korniykom.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestSerializable(
     val email: String,
     val password: String,
)