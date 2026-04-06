package com.korniykom.webvetcare.presentation.screens.landing_page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.korniykom.webvetcare.presentation.components.particle_background.ParticleBackground
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LandingPageRoot(
    viewModel: LandingPageViewModel = koinViewModel(),
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ParticleBackground(
            backgroundGradientColorStart = MaterialTheme.colorScheme.secondary,
            backgroundGradientColorEnd = MaterialTheme.colorScheme.primary,
            particlesColor = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.matchParentSize()
        )
    }
}