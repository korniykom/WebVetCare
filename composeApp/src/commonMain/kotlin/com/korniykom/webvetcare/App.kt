package com.korniykom.webvetcare

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButton
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButtonStyle
import com.korniykom.webvetcare.presentation.theme.WebVetCareTheme


@Composable
fun App() {
    WebVetCareTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            WebVetCareButton(
                text = "login",
                onClick = {},
                modifier = Modifier,
                style = WebVetCareButtonStyle.YELLOW,
                enabled = true,
                isLoading = false
            )
            WebVetCareButton(
                text = "login",
                onClick = {},
                modifier = Modifier,
                style = WebVetCareButtonStyle.WHITE,
                enabled = true,
                isLoading = false
            )
            WebVetCareButton(
                text = "login",
                onClick = {},
                modifier = Modifier,
                style = WebVetCareButtonStyle.TEAL,
                enabled = true,
                isLoading = false
            )
            WebVetCareButton(
                text = "login",
                onClick = {},
                modifier = Modifier,
                style = WebVetCareButtonStyle.TEAL_GHOST,
                enabled = true,
                isLoading = false
            )
        }
    }
}