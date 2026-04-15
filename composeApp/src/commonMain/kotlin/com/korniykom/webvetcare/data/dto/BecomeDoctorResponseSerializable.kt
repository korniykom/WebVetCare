package com.korniykom.webvetcare.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BecomeDoctorResponseSerializable (
    val id: String,
    val specialization: String,
    val licenseNumber: String,
    val availability: String,
    val clinicAddress: String,
)