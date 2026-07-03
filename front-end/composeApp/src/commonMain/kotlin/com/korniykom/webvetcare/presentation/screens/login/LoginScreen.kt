package com.korniykom.webvetcare.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.korniykom.webvetcare.presentation.components.layouts.LoginScreenLayout
import com.korniykom.webvetcare.presentation.components.login_components.LoginForm
import com.korniykom.webvetcare.presentation.components.login_components.LoginFreaks
import com.korniykom.webvetcare.presentation.components.snackbars.SnackBarType
import com.korniykom.webvetcare.presentation.components.snackbars.WebVetCareSnackBar
import com.korniykom.webvetcare.presentation.components.snackbars.show
import com.korniykom.webvetcare.presentation.theme.UnicornSilver
import com.korniykom.webvetcare.presentation.util.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreenRoot(
    navigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is LoginEvent.LoginSuccess -> {
                onLoginSuccess()
            }

            is LoginEvent.LoginFailure -> {
                snackbarHostState.show("Login Failed", SnackBarType.ERROR)
            }
        }
    }
    LoginScreen(
        snackbarHostState = snackbarHostState,
        navigateToRegister = navigateToRegister,
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        },
        modifier = modifier,
    )
}

@Composable
fun LoginScreen(
    snackbarHostState: SnackbarHostState,
    navigateToRegister: () -> Unit,
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        snackbarHost = { WebVetCareSnackBar(snackbarHostState) }
    ) {
        LoginScreenLayout() {
            Box(
                modifier = Modifier
                    .height(856.dp)
                    .width(1284.dp)
                    .background(
                        color = UnicornSilver,
                        shape = RoundedCornerShape(32.dp)
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    LoginFreaks(
                        modifier = Modifier
                            .weight(3.0f)
                            .fillMaxSize(),
                        freaksLookState = state.freaksLookState,
                    )
                    LoginForm(
                        onPasswordFiledActive = {onAction(LoginAction.OnLookOnPasswordField(it))},
                        onEmailFieldActive = { onAction(LoginAction.OnLookOnEmailField(it))},
                        modifier = Modifier
                            .weight(2.0f)
                            .fillMaxSize(),
                        emailTextFieldState = state.emailTextFieldState,
                        passwordTextFieldState = state.passwordTextFieldState,
                        onTogglePasswordVisibilityClick = { onAction(LoginAction.OnTogglePasswordVisibilityClick) },
                        isPasswordVisible = state.isPasswordVisible,
                        onLoginClick = { onAction(LoginAction.OnLoginClick) },
                        navigateToRegister = navigateToRegister,
                    )
                }
            }
        }
    }
}