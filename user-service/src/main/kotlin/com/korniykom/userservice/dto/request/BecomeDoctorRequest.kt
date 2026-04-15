package com.korniykom.userservice.dto.request

data class BecomeDoctorRequest(
    val specialization: String,
    val licenseNumber: String,
    val clinicAddress: String,
    val availability: String,
)