package com.korniykom.webvetcare.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BecomeDoctorRequestSerializable (
    val specialization: String,
    val licenseNumber: String,
    val clinicAddress: String,
    val availability: String
)