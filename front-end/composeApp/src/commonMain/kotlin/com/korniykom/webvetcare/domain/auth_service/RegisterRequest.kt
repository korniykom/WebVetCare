package com.korniykom.webvetcare.domain.auth_service

data class RegisterRequest(
    val email: String,
    val password: String,
    val username: String,
)