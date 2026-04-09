package com.korniykom.webvetcare.presentation.screens.login

sealed interface LoginAction {
    data object OnLoginClick : LoginAction
    data object OnGoToRegisterClick : LoginAction
    data object OnForgotPasswordClick : LoginAction
    data object OnTogglePasswordVisibilityClick : LoginAction
}