package com.korniykom.webvetcare.domain.util

sealed class AuthEvent {
    object SessionExpired : AuthEvent()
}