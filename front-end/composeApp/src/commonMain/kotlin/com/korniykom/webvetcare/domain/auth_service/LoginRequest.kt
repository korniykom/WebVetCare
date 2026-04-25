package com.korniykom.webvetcare.domain.auth_service

data class LoginRequest(
    val email: String,
    val password: String
)