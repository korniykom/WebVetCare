package com.korniykom.webvetcare.presentation.screens.login

import com.korniykom.webvetcare.presentation.screens.register.RegisterEvent

sealed interface LoginEvent {
    data object Success: LoginEvent
}