package com.korniykom.webvetcare.domain.user_service

data class GetUserInfoResponse (
    val id: String,
    val email: String,
    val roles: List<String>,
    val username: String,
)