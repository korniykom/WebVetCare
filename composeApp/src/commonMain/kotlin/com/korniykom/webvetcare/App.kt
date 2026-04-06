package com.korniykom.webvetcare

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButton
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButtonStyle
import com.korniykom.webvetcare.presentation.components.particle_background.ParticleBackground
import com.korniykom.webvetcare.presentation.screens.landing_page.LandingPageRoot
import com.korniykom.webvetcare.presentation.theme.WebVetCareTheme


@Composable
fun App() {
    WebVetCareTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            ParticleBackground(
                backgroundColor = MaterialTheme.colorScheme.secondary,
                particlesColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.matchParentSize()
            )
        }
    }
}