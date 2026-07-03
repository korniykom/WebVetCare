package com.korniykom.webvetcare.presentation.screens.login

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.webvetcare.domain.auth_service.AuthService
import com.korniykom.webvetcare.domain.util.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
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
            is LoginAction.OnLookOnPasswordField -> {
                when (action.isPasswordFieldActive) {
                    true -> if(!state.value.isPasswordVisible) {
                        _state.update { it.copy(
                            freaksLookState = FreaksLookState.PASSWORD_FIELD_HIDDEN,
                        ) }
                    } else {
                        _state.update { it.copy(
                            freaksLookState = FreaksLookState.PASSWORD_FIELD_VISIBLE,
                        ) }
                    }

                    else -> _state.update {
                        it.copy(
                            freaksLookState = FreaksLookState.NONE,
                        )
                    }
                }


            }
            is LoginAction.OnLookOnEmailField -> {
                _state.update { it.copy(
                    isPasswordVisible = false
                ) }
                when (action.isEmailFieldActive) {
                    true -> _state.update {
                        it.copy(
                            freaksLookState = FreaksLookState.EMAIL_FIELD,
                        )
                    }

                    else -> _state.update {
                        it.copy(
                            freaksLookState = FreaksLookState.NONE,
                        )
                    }
                }


            }

            LoginAction.OnForgotPasswordClick -> {}
            LoginAction.OnLoginClick -> {
                login()
            }

            LoginAction.OnGoToRegisterClick -> {}
            LoginAction.OnTogglePasswordVisibilityClick -> {
                _state.update {
                    val visible = !it.isPasswordVisible

                    it.copy(
                        isPasswordVisible = visible,
                        freaksLookState = when {
                            visible -> FreaksLookState.PASSWORD_FIELD_VISIBLE
                            else -> FreaksLookState.PASSWORD_FIELD_HIDDEN
                        }
                    )
                }
            }


        }
    }

}