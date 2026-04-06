package com.korniykom.webvetcare.presentation.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.korniykom.webvetcare.presentation.theme.WebVetCareTheme
import com.korniykom.webvetcare.presentation.theme.bodyBold
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
    isLoading: Boolean = false,
) {
    val colors = when(style) {
        WebVetCareButtonStyle.YELLOW -> ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.tertiary,
        )
        WebVetCareButtonStyle.WHITE -> ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.background,
        )
        WebVetCareButtonStyle.TEAL -> ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary,
        )
        WebVetCareButtonStyle.TEAL_GHOST -> ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.onPrimary,
        )
    }

    val defaultBorderStroke = BorderStroke(
        width = 1.dp,
        color = MaterialTheme.colorScheme.extended.transparent
    )

    val border = when(style) {
        WebVetCareButtonStyle.YELLOW -> defaultBorderStroke
        WebVetCareButtonStyle.WHITE ->  defaultBorderStroke
        WebVetCareButtonStyle.TEAL ->  defaultBorderStroke
        WebVetCareButtonStyle.TEAL_GHOST -> BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.primary
        )
    }

    Button(
        colors = colors,
        onClick = onClick,
        enabled =  enabled,
        modifier = modifier,
        border = border,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyBold
            )
        }
    }
}
