package com.korniykom.auth.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController {
    @PostMapping("/register")
    fun register() {
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