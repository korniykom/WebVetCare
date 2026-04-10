package com.korniykom.webvetcare.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseSerializable(
    val accessToken: String,
)