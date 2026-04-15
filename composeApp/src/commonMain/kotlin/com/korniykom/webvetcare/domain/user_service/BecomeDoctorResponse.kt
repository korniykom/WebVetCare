package com.korniykom.webvetcare.domain.user_service

data class BecomeDoctorResponse(
    val id: String,
    val specialization: String,
    val licenseNumber: String,
    val availability: String,
    val clinicAddress: String,
)