package com.korniykom.userservice.dto.request

data class BecomePatientRequest(
    val contactEmail: String,
    val contactPhoneNumber: String
)