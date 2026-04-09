package com.korniykom.webvetcare.data.dto

import kotlinx.serialization.Serializable

@Serializable
class RegisterRequestSerializable(
    val email: String,
    val password: String
) {
}