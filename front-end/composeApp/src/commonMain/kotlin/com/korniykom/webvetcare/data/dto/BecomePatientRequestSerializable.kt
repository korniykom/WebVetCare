package com.korniykom.webvetcare.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BecomePatientRequestSerializable(
    val contactEmail: String,
    val contactPhoneNumber: String
)