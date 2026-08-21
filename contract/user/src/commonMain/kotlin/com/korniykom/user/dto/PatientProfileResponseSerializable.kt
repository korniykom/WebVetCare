package com.korniykom.user.dto

import kotlinx.serialization.Serializable

@Serializable
data class PatientProfileResponse(
    val id: String,
    val contactEmail: String,
    val contactPhoneNumber: String
)