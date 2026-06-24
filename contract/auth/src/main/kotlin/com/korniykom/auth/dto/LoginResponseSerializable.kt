package com.korniykom.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseSerializable(
    val accessToken: String,
)