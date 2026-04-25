package com.korniykom.webvetcare.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(val accessToken: String)