package com.korniykom.webvetcare.presentation.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.korniykom.webvetcare.presentation.theme.WebVetCareTheme

import com.korniykom.webvetcare.presentation.theme.extended

enum class WebVetCareButtonStyle {
    YELLOW,
    WHITE,
    TEAL,
    TEAL_GHOST
}

@Composable
fun WebVetCareButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: WebVetCareButtonStyle = WebVetCareButtonStyle.TEAL,
    enabled: Boolean = true,
) {
    val colors = when (style) {
        WebVetCareButtonStyle.YELLOW -> ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onTertiary,
            containerColor = MaterialTheme.colorScheme.tertiary,
            disabledContentColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = .6f),
            disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = .6f)
        )

        WebVetCareButtonStyle.WHITE -> ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.background,
            containerColor = MaterialTheme.colorScheme.onBackground,
            disabledContentColor = MaterialTheme.colorScheme.background.copy(alpha = .6f),
            disabledContainerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f)
        )

        WebVetCareButtonStyle.TEAL -> ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.background,
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.background.copy(alpha = .6f),
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = .6f)
        )

        WebVetCareButtonStyle.TEAL_GHOST -> ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = .6f),
            disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = .6f)
        )
    }

    val defaultBorderStroke = BorderStroke(
        width = 1.dp,
        color = MaterialTheme.colorScheme.extended.transparent
    )

    val border = when (style) {
        WebVetCareButtonStyle.YELLOW -> defaultBorderStroke
        WebVetCareButtonStyle.WHITE -> defaultBorderStroke

        WebVetCareButtonStyle.TEAL -> defaultBorderStroke
        WebVetCareButtonStyle.TEAL_GHOST -> BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Button(
        colors = colors,
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        border = border,
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(4.dp)

            )
        }
    }
}


@Preview
@Composable
fun WebVetCareButtonYellow() {
    WebVetCareTheme {
        WebVetCareButton(
            text = "Login",
            onClick = {},
            style = WebVetCareButtonStyle.YELLOW,
        )
    }
}