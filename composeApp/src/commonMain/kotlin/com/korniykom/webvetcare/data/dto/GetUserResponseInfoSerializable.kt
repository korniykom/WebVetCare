package com.korniykom.webvetcare.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GetUserResponseInfoSerializable (
    val id: String,
    val email: String,
    val roles: List<String>,
    val username: String,
)