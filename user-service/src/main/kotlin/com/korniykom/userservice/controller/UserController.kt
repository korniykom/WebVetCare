package com.korniykom.userservice.controller

import com.korniykom.userservice.dto.response.UserResponse
import com.korniykom.userservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/me")
    fun getMe(@AuthenticationPrincipal jwt: Jwt): ResponseEntity<UserResponse> {
         val userId = jwt.subject
        val email = jwt.getClaim<String>("email")
        return ResponseEntity.ok(userService.getOrCreateUser(userId, email))
    }
}