package com.korniykom.userservice.controller

import com.korniykom.userservice.dto.request.BecomeDoctorRequest
import com.korniykom.userservice.dto.response.DoctorProfileResponse
import com.korniykom.userservice.service.DoctorService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class DoctorController(
    private val doctorService: DoctorService
) {
    @PostMapping("/become-doctor")
    fun becomeDoctor(
        @AuthenticationPrincipal jwt: Jwt,
        @RequestBody body: BecomeDoctorRequest
    ): ResponseEntity<DoctorProfileResponse> {
        val userId = jwt.subject
        return ResponseEntity.ok(doctorService.becomeDoctor(userId, body))
    }

    @GetMapping("/doctor-profile")
    fun getDoctorProfile(
        @AuthenticationPrincipal jwt: Jwt
    ): ResponseEntity<DoctorProfileResponse> {
        val userId = jwt.subject
        return ResponseEntity.ok(doctorService.getDoctorProfile(userId))
    }
}