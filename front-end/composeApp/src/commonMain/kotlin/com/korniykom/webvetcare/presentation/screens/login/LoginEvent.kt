package com.korniykom.webvetcare.presentation.screens.login

sealed interface LoginEvent {
    data object LoginSuccess: LoginEvent
    data object LoginFailure: LoginEvent
}