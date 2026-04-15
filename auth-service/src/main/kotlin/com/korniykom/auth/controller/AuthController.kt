package com.korniykom.auth.controller

import com.korniykom.auth.config.JwtConfig
import com.korniykom.auth.dto.LoginRequest
import com.korniykom.auth.dto.RegisterRequest
import com.korniykom.auth.dto.TokenResponse
import com.korniykom.auth.service.AuthService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
    private val jwtConfig: JwtConfig,
) {
    @PostMapping("/register")
    fun register(
        @Valid @RequestBody body: RegisterRequest,
        response: HttpServletResponse
    ): ResponseEntity<TokenResponse> {
        val (accessToken, refreshToken) = authService.register(
            email = body.email,
            password = body.password,
            username = body.username
        )

        val cookie = Cookie("refresh_token", refreshToken).apply {
            isHttpOnly = true
            secure = false
            path = "/"
            maxAge = jwtConfig.refreshTokenExpiry!!.toInt()
        }

        response.addCookie(cookie)

        return ResponseEntity.status(HttpStatus.CREATED).body(TokenResponse(accessToken))

    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody body: LoginRequest,
        response: HttpServletResponse
    ): ResponseEntity<TokenResponse> {
        val (accessToken, refreshToken) = authService.login(
            email = body.email,
            password = body.password
        )

        val cookie = Cookie("refresh_token", refreshToken).apply {
            isHttpOnly = true
            secure = false
            path = "/api/auth/refresh"
            maxAge = jwtConfig.refreshTokenExpiry!!.toInt()
        }

        response.addCookie(cookie)

        return ResponseEntity.ok(TokenResponse(accessToken))
    }

    @PostMapping("/refresh")
    fun refresh(
        @CookieValue("refresh_token") refreshToken: String,
        response: HttpServletResponse
    ): ResponseEntity<TokenResponse> {
        val (accessToken, newRefreshToken) = authService.refresh(refreshToken)

        val cookie = Cookie("refresh_token", newRefreshToken).apply {
            isHttpOnly = true
            secure = false
            path = "/api/auth/refresh"
            maxAge = jwtConfig.refreshTokenExpiry!!.toInt()
        }
        response.addCookie(cookie)

        return ResponseEntity.ok(TokenResponse(accessToken))
    }

    @PostMapping("/logout")
    fun logout(
        @CookieValue("refresh_token") refreshToken: String,
        response: HttpServletResponse
    ): ResponseEntity<Void> {
        authService.logout(refreshToken)

        val cookie = Cookie("refresh_token", "").apply {
            isHttpOnly = true
            secure = false
            path = "/api/auth/refresh"
            maxAge = 0
        }
        response.addCookie(cookie)

        return ResponseEntity.noContent().build()
    }
}