package com.korniykom.webvetcare.presentation.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldLayout(
    title: String? = null,
    isError: Boolean = false,
    supportingText: String? = null,
    enabled: Boolean = true,
    onFocusChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    textField: @Composable (Modifier, MutableInteractionSource) -> Unit,

    ) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isFocused) {
        onFocusChange(isFocused)
    }

    val textFieldStyleModifier = Modifier
        .fillMaxWidth()
        .background(
            color = textFieldContainerColor(enabled = enabled, isFocused = isFocused, isError = isError),
            shape = RoundedCornerShape(8.dp)
        )
        .border(
            width = if (isFocused) 2.dp else 1.dp,
            color = textFieldBorderColor(isError, isFocused),
            shape = RoundedCornerShape(8.dp)
        )
        .padding(12.dp)

    Column(modifier = modifier) {
        if(title != null) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 13.sp
                ),
                color = textFieldTitleColor(isError = isError, enabled = enabled),            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        textField(textFieldStyleModifier, interactionSource)

        if(supportingText != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = supportingText,
                color = if(isError) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.tertiary
                },
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 13.sp
                )
            )
        }
    }

}