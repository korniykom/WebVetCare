package com.korniykom.auth.controller

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
        @Valid @RequestBody request: RegisterRequest
    ): ResponseEntity<TokenResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request))
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