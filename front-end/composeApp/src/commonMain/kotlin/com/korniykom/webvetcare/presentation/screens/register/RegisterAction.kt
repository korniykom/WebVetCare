package com.korniykom.webvetcare.presentation.screens.register

sealed interface RegisterAction {
    data object OnCreateAccountClick : RegisterAction
    data object OnTogglePasswordVisibilityClick : RegisterAction
}