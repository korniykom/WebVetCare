package com.korniykom.webvetcare.presentation.screens.login

import androidx.compose.foundation.text.input.TextFieldState

data class LoginState(
    val emailTextFieldState: TextFieldState = TextFieldState(),
    val passwordTextFieldState: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val canLogin: Boolean = false,
    val error: String? = null
)
