package com.korniykom.webvetcare.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestSerializable(
    val email: String,
    val password: String,
)