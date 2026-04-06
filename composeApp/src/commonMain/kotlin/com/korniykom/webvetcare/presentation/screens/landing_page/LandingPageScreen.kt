package com.korniykom.webvetcare.presentation.screens.landing_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButton
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButtonStyle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LandingPageRoot(
    viewModel: LandingPageViewModel = koinViewModel(),
) {
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