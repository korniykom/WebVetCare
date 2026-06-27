package com.korniykom.webvetcare.presentation.components.login_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButton
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButtonStyle
import com.korniykom.webvetcare.presentation.components.logos.PawLogo
import com.korniykom.webvetcare.presentation.components.textfields.PasswordTextField
import com.korniykom.webvetcare.presentation.components.textfields.TextField
import com.korniykom.webvetcare.presentation.theme.NeutralVar40
import com.korniykom.webvetcare.presentation.theme.SatinDeepBlack
import com.korniykom.webvetcare.presentation.theme.WhiteAsHeaven

@Composable
fun LoginForm(
    onPasswordFiledActive: (active: Boolean) -> Unit,
    onEmailFieldActive: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    emailTextFieldState: TextFieldState,
    passwordTextFieldState: TextFieldState,
    onTogglePasswordVisibilityClick: () -> Unit,
    isPasswordVisible: Boolean,
    onLoginClick: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    Column(
        modifier = modifier

            .background(
                color = WhiteAsHeaven,
                shape = RoundedCornerShape(
                    32.dp
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PawLogo()
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = "Welcome back!",
            style = MaterialTheme.typography.displayMedium.copy(
                color = SatinDeepBlack,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            ),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Please enter your credentials!",
            style = MaterialTheme.typography.titleMedium.copy(
                color = SatinDeepBlack,
                fontFamily = FontFamily.Monospace
            ),
        )
        Spacer(modifier = Modifier.height(36.dp))
        TextField(
            onFocusChange = onEmailFieldActive,
            state = emailTextFieldState,
            placeholder = "Enter your email",
            title = "Email",
            modifier = Modifier.padding(horizontal = 32.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordTextField(
            onFocusChange = onPasswordFiledActive,
            state = passwordTextFieldState,
            placeholder = "Create your password",
            title = "Password",
            isPasswordVisible = isPasswordVisible,
            onToggleVisibilityClick = { onTogglePasswordVisibilityClick() },
            modifier = Modifier.padding(horizontal = 32.dp),
        )
        Spacer(modifier = Modifier.height(48.dp))
        WebVetCareButton(
            style = WebVetCareButtonStyle.TEAL,
            text = "Log in",
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        )
        Spacer(modifier = Modifier.height(64.dp))

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