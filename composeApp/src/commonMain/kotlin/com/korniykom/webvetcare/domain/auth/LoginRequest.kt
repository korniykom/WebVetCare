package com.korniykom.webvetcare.domain.auth

data class LoginRequest(
    val email: String,
    val password: String
)