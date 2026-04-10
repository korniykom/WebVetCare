package com.korniykom.webvetcare.presentation.screens.register

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.webvetcare.domain.auth.AuthService
import com.korniykom.webvetcare.domain.logging.WebVetCareLogger
import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.EmailValidator
import com.korniykom.webvetcare.domain.util.TokenStorage
import com.korniykom.webvetcare.domain.util.onFailure
import com.korniykom.webvetcare.domain.util.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authService: AuthService,
    private val webVetCareLogger: WebVetCareLogger,
    private val tokenStorage: TokenStorage,
) : ViewModel() {
    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()
    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RegisterState())
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
            initialValue = RegisterState()
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
                    canCreateAccount = isEmailValid && isPasswordNotBlank
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun register() {
        if(!validateFormInput()) {
            return
        }

        val email = state.value.emailTextFieldState.text.toString()
        val password = state.value.passwordTextFieldState.text.toString()

        viewModelScope.launch {
            authService.register(
                email = email,
                password = password
            )
                .onSuccess { responseBody ->
                    eventChannel.send(RegisterEvent.Success)
                    tokenStorage.saveAccessToken(responseBody.accessToken)
                }
                .onFailure { error ->
                    webVetCareLogger.error("Registration failed with error: $error")
                    val registrationError = when(error) {
                        DataError.Remote.CONFLICT -> "User already exists"
                        else -> error.toString()
                    }
                    _state.update { it.copy(error = registrationError) }

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

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.OnCreateAccountClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                _state.update {
                    it.copy(
                        isPasswordVisible = !it.isPasswordVisible
                    )
                }
            }
        }
    }
}