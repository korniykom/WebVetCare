package com.korniykom.webvetcare.presentation.screens.login

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.webvetcare.domain.auth_service.AuthService
import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.EmailValidator
import com.korniykom.webvetcare.domain.util.TokenStorage
import com.korniykom.webvetcare.domain.util.onFailure
import com.korniykom.webvetcare.domain.util.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authService: AuthService,
    private val tokenStorage: TokenStorage
) : ViewModel() {
    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()
    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(LoginState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                observeTextStates()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LoginState()
        )

    private val isEmailValidFlow = snapshotFlow { state.value.emailTextFieldState.text.toString() }
        .map { email -> EmailValidator.validate(email) }
        .distinctUntilChanged()

    private val isPasswordNotBlankFlow =
        snapshotFlow { state.value.passwordTextFieldState.text.toString() }
            .map { password -> password.isNotBlank() }
            .distinctUntilChanged()

    private fun observeTextStates() {
        combine(
            isEmailValidFlow,
            isPasswordNotBlankFlow
        ) { isEmailValid, isPasswordNotBlank ->
            _state.update {
                it.copy(
                    canLogin = isEmailValid && isPasswordNotBlank
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun login() {
        if (!validateFormInput()) {
            return
        }

        val email = state.value.emailTextFieldState.text.toString()
        val password = state.value.passwordTextFieldState.text.toString()

        viewModelScope.launch {
            authService.login(
                email = email,
                password = password
            )
                .onSuccess { responseBody ->
                    eventChannel.send(LoginEvent.LoginSuccess)
                    tokenStorage.saveAccessToken(responseBody.accessToken)
                }
                .onFailure { error ->
                    eventChannel.send(LoginEvent.LoginFailure)
                    val loginError = when (error) {
                        DataError.Remote.NOT_FOUND -> "User not found"
                        DataError.Remote.UNAUTHORIZED -> "You entered wrong credentials"
                        else -> error.toString()
                    }
                    _state.update { it.copy(error = loginError) }
                }
        }
    }

    private fun validateFormInput(): Boolean {
        val currentState = state.value
        val email = currentState.emailTextFieldState.text.toString()
        val password = currentState.passwordTextFieldState.text.toString()

        val isEmailValid = EmailValidator.validate(email)
        val isPasswordValid = password.isNotEmpty()

        return isEmailValid && isPasswordValid
    }

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.OnForgotPasswordClick -> {}
            LoginAction.OnLoginClick -> {
                login()
            }

            LoginAction.OnGoToRegisterClick -> {}
            LoginAction.OnTogglePasswordVisibilityClick -> {
                _state.update {
                    it.copy(
                        isPasswordVisible = !it.isPasswordVisible
                    )
                }
            }
        }
    }

}