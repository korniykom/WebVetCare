package com.korniykom.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class RegisterRequest(
    @field:Email val email: String,
    @field:NotBlank val password: String,
)