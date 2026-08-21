package com.korniykom.userservice.controller

import com.korniykom.user.dto.BecomePatientRequest
import com.korniykom.user.dto.PatientProfileResponse
import com.korniykom.userservice.service.PatientService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class PatientController(
    private val patientService: PatientService
) {
    @PostMapping("/become-patient")
    fun becomePatient(
        @AuthenticationPrincipal jwt: Jwt, @RequestBody body: BecomePatientRequest
    ): ResponseEntity<PatientProfileResponse> {
        val userId = jwt.subject
        return ResponseEntity.ok(patientService.becomePatient(userId, body))
    }

    @GetMapping("/patient-profile")
    fun getPatientProfile(
        @AuthenticationPrincipal jwt: Jwt
    ): ResponseEntity<PatientProfileResponse> {
        val userId = jwt.subject
        return ResponseEntity.ok(patientService.getPatientProfile(userId))
    }
}