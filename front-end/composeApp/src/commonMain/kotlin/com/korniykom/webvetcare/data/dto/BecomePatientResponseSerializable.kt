package com.korniykom.webvetcare.data.dto

import kotlinx.serialization.Serializable

@Serializable
class BecomePatientResponseSerializable (
    val id: String,
    val contactEmail: String,
    val contactPhoneNumber: String
)