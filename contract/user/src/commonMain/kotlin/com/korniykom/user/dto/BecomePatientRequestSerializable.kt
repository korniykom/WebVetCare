package com.korniykom.user.dto

import kotlinx.serialization.Serializable

@Serializable
data class BecomePatientRequest(
    val contactEmail: String,
    val contactPhoneNumber: String
)