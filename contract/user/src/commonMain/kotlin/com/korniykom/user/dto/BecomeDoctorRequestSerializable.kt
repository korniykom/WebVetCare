package com.korniykom.user.dto

import kotlinx.serialization.Serializable

@Serializable
data class BecomeDoctorRequest(
    val specialization: String,
    val licenseNumber: String,
    val clinicAddress: String,
    val availability: String,
)