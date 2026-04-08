package com.korniykom.webvetcare.presentation.components.textfields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import com.korniykom.webvetcare.presentation.theme.bodyBold
import com.korniykom.webvetcare.presentation.theme.bodyRegular

@Composable
fun TextField(
    state: TextFieldState,
    placeholder: String? = null,
    title: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    onFocusChange: (Boolean) -> Unit = {},
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    TextFieldLayout(
        title = title,
        isError = isError,
        supportingText = supportingText,
        enabled = enabled,
        onFocusChange = onFocusChange,
        modifier = Modifier,
    ) { styleModifier, interactionSource ->
        BasicTextField(
            state = state,
            enabled = enabled,
            lineLimits = if(singleLine) {
                TextFieldLineLimits.SingleLine
            } else {
                TextFieldLineLimits.Default
            },
            textStyle = MaterialTheme.typography.bodyBold.copy(
                color = textFieldTextColor(enabled, isError)
            ),
            interactionSource = interactionSource,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            modifier = styleModifier,
            decorator = { innerBox ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
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
            }
        )
    }
}