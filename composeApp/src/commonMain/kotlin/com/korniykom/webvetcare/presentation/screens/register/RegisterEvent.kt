package com.korniykom.webvetcare.presentation.screens.register

sealed interface RegisterEvent {
    data object Success: RegisterEvent
}