package com.korniykom.userservice.dto.response

data class DoctorProfileResponse(
    val id: String,
    val specialization: String,
    val licenseNumber: String,
    val clinicAddress: String,
    val availability: String,
)