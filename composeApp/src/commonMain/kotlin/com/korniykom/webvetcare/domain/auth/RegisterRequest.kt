package com.korniykom.webvetcare.domain.auth

data class RegisterRequest(
    val email: String,
    val password: String
)