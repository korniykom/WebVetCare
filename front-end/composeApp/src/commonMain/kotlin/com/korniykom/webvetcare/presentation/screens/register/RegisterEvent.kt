package com.korniykom.webvetcare.presentation.screens.register

sealed interface RegisterEvent {
    data object RegisterSuccess: RegisterEvent
    data object RegisterFailure: RegisterEvent
}