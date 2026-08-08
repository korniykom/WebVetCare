package com.korniykom.auth.dto

import kotlinx.serialization.Serializable

@Serializable
class RegisterResponseSerializable(
    val accessToken: String
)