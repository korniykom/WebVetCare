package com.korniykom.webvetcare.presentation.screens.login

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButton
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButtonStyle
import com.korniykom.webvetcare.presentation.components.layouts.AdaptiveFormLayout
import com.korniykom.webvetcare.presentation.components.textfields.PasswordTextField
import com.korniykom.webvetcare.presentation.components.textfields.TextField
import com.korniykom.webvetcare.presentation.theme.NeutralVar40
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

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is LoginEvent.Success -> {
                onLoginSuccess()
            }
        }
    }
    LoginScreen(
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
    navigateToRegister: () -> Unit,
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier
) {
    AdaptiveFormLayout(
        headerText = "Welcome back",
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
                placeholder = "Enter your password",
                title = "Password",
                isPasswordVisible = state.isPasswordVisible,
                onToggleVisibilityClick = { onAction(LoginAction.OnTogglePasswordVisibilityClick) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Forgot password?",
                style = MaterialTheme.typography.bodySmall.copy(
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.End,
                    color = NeutralVar40
                ),
                modifier = Modifier.fillMaxWidth()
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
                text = "Login",
                style = WebVetCareButtonStyle.TEAL,
                onClick = {onAction(LoginAction.OnLoginClick)},
                enabled = state.canLogin,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.bodySmall.copy(
                        textDecoration = TextDecoration.None,
                        textAlign = TextAlign.End,
                        color = NeutralVar40
                    ),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.bodySmall.copy(
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.End,
                        color = NeutralVar40
                    ),
                    modifier = Modifier.clickable(
                        onClick = navigateToRegister
                    )
                )
            }

        }
    }
}