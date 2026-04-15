package com.korniykom.webvetcare.presentation.screens.register

import androidx.compose.foundation.text.input.TextFieldState

data class RegisterState(
    val emailTextFieldState: TextFieldState = TextFieldState(),
    val passwordTextFieldState: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val canCreateAccount: Boolean = false,
    val error: String? = null,
)