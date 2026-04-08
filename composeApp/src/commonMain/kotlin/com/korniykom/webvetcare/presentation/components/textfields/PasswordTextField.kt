package com.korniykom.webvetcare.presentation.components.textfields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.korniykom.webvetcare.presentation.theme.bodyBold
import com.korniykom.webvetcare.presentation.theme.bodyRegular
import org.jetbrains.compose.resources.vectorResource
import webvetcare.composeapp.generated.resources.Res
import webvetcare.composeapp.generated.resources.eye_icon
import webvetcare.composeapp.generated.resources.icon_eye_off

@Composable
fun PasswordTextField(
    state: TextFieldState,
    isPasswordVisible: Boolean,
    onToggleVisibilityClick: () -> Unit,
    placeholder: String? = null,
    title: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    onFocusChange: (Boolean) -> Unit = {},
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {

    TextFieldLayout(
        title = title,
        isError = isError,
        supportingText = supportingText,
        enabled = enabled,
        onFocusChange = onFocusChange,
        modifier = modifier
    ) { styleModifier, interactionSource ->

        val isFocused by interactionSource.collectIsFocusedAsState()
        BasicSecureTextField(
            state = state,
            enabled = enabled,
            textObfuscationMode = if (isPasswordVisible) {
                TextObfuscationMode.Visible
            } else {
                TextObfuscationMode.Hidden
            },
            textStyle = MaterialTheme.typography.bodyBold.copy(
                color = textFieldTextColor(enabled, isError)
            ),
            interactionSource = interactionSource,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = styleModifier,
            decorator = { innerBox ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (state.text.isEmpty() && placeholder != null) {
                            Text(
                                text = placeholder,
                                color = textFieldPlaceholderColor(isError),
                                style = MaterialTheme.typography.bodyRegular
                            )
                        }
                        innerBox()
                    }
                    Icon(
                        imageVector = if (isPasswordVisible) {
                            vectorResource(Res.drawable.icon_eye_off)
                        } else {
                            vectorResource(Res.drawable.eye_icon)
                        },
                        contentDescription = if (isPasswordVisible) {
                            "Hide password"
                        } else {
                            "Show password"
                        },
                        tint = textFieldIconColor(
                            enabled = enabled,
                            isFocused = isFocused,
                            isError = isError,
                        ),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(
                                    bounded = false,
                                    radius = 24.dp
                                ),
                                onClick = onToggleVisibilityClick
                            )

                    )
                }
            }
        )
    }
}