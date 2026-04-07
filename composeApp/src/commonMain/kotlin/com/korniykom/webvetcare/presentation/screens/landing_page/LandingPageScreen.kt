package com.korniykom.webvetcare.presentation.screens.landing_page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButton
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButtonStyle
import com.korniykom.webvetcare.presentation.components.logos.PawLogo
import com.korniykom.webvetcare.presentation.components.particle_background.ParticleBackground
import com.korniykom.webvetcare.presentation.theme.H0
import com.korniykom.webvetcare.presentation.theme.H1
import com.korniykom.webvetcare.presentation.theme.H3
import com.korniykom.webvetcare.presentation.theme.bodyRegular
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LandingPageRoot(
    viewModel: LandingPageViewModel = koinViewModel(),
) {
    LandingPageScreen()
}

@Composable
fun LandingPageScreen(

) {
    Box(modifier = Modifier.fillMaxSize()) {
        ParticleBackground(
            backgroundGradientColorStart = MaterialTheme.colorScheme.secondary,
            backgroundGradientColorEnd = MaterialTheme.colorScheme.primary,
            particlesColor = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.matchParentSize()
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PawLogo()
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "WebVetCare",
                style = MaterialTheme.typography.H0,
                color = MaterialTheme.colorScheme.onSecondary,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Your trusted digital veterinary care platform —\n" +
                        "book appointments, manage health records, connect with doctors.",
                style = MaterialTheme.typography.bodyRegular,
                color = Color(0xFFB8D4EB),
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )
            Spacer(modifier = Modifier.height(28.dp))
            Row {
                WebVetCareButton(
                    text = "Login",
                    onClick = {},
                    style = WebVetCareButtonStyle.YELLOW
                )
                Spacer(modifier = Modifier.width(16.dp))
                WebVetCareButton(
                    text = "Register",
                    onClick = {},
                    style = WebVetCareButtonStyle.WHITE
                )
            }
        }

    }
}