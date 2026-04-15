package com.korniykom.webvetcare.presentation.screens.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButton
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButtonStyle
import com.korniykom.webvetcare.presentation.components.layouts.AdaptiveFormLayout
import com.korniykom.webvetcare.presentation.components.snackbars.SnackBarType
import com.korniykom.webvetcare.presentation.components.snackbars.WebVetCareSnackBar
import com.korniykom.webvetcare.presentation.components.snackbars.show
import com.korniykom.webvetcare.presentation.components.textfields.PasswordTextField
import com.korniykom.webvetcare.presentation.components.textfields.TextField
import com.korniykom.webvetcare.presentation.theme.NeutralVar40
import com.korniykom.webvetcare.presentation.util.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreenRoot(
    navigateToLoginScreen: () -> Unit,
    onRegisterSuccess: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RegisterEvent.RegisterSuccess -> {
                onRegisterSuccess()
//                snackbarHostState.show("Register Successful", SnackBarType.SUCCESS)
            }
            is RegisterEvent.RegisterFailure -> {
                snackbarHostState.show("Register Failed", SnackBarType.ERROR)
            }
        }
    }

    RegisterScreen(
        snackbarHostState = snackbarHostState,
        navigateToLoginScreen = navigateToLoginScreen,
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        },
        modifier = modifier,
    )
}

@Composable
fun RegisterScreen(
    snackbarHostState: SnackbarHostState,
    navigateToLoginScreen: () -> Unit,
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        snackbarHost = { WebVetCareSnackBar(snackbarHostState) }
    ) {
        AdaptiveFormLayout(
            headerText = "Create an account",
            modifier = modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    state = state.emailTextFieldState,
                    placeholder = "Enter your email",
                    title = "Email",
                )

                Spacer(modifier = Modifier.height(12.dp))

                PasswordTextField(
                    state = state.passwordTextFieldState,
                    placeholder = "Create your password",
                    title = "Password",
                    isPasswordVisible = state.isPasswordVisible,
                    onToggleVisibilityClick = { onAction(RegisterAction.OnTogglePasswordVisibilityClick) }
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (state.error != null) {
                    Text(
                        text = state.error,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.errorContainer,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                WebVetCareButton(
                    text = "Create account",
                    style = WebVetCareButtonStyle.TEAL,
                    onClick = { onAction(RegisterAction.OnCreateAccountClick) },
                    enabled = state.canCreateAccount,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Already have an account?",
                        style = MaterialTheme.typography.bodySmall.copy(
                            textDecoration = TextDecoration.None,
                            textAlign = TextAlign.End,
                            color = NeutralVar40
                        ),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.bodySmall.copy(
                            textDecoration = TextDecoration.Underline,
                            textAlign = TextAlign.End,
                            color = NeutralVar40
                        ),
                        modifier = Modifier.clickable(
                            onClick = navigateToLoginScreen
                        )
                    )
                }

            }
        }
    }
}