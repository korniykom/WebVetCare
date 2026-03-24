package com.korniykom.auth.controller

import com.korniykom.auth.domain.type.User
import com.korniykom.auth.dto.RegisterRequest
import com.korniykom.auth.dto.TokenResponse
import com.korniykom.auth.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/register")
    fun register(
        @Valid @RequestBody body: RegisterRequest
    ): User {
        return authService.register(
            email = body.email,
            password = body.password,
            username = body.username
        )
    }

    @PostMapping("/login")
    fun login() {
    }

    @PostMapping("/refresh")
    fun refresh() {
    }

    @PostMapping("/logout")
    fun logout() {
    }
}