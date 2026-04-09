package com.korniykom.webvetcare.presentation.screens.landing_page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButton
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButtonStyle
import com.korniykom.webvetcare.presentation.components.logos.PawLogo
import com.korniykom.webvetcare.presentation.components.particle_background.ParticleBackground
import com.korniykom.webvetcare.presentation.theme.DarkNavy
import com.korniykom.webvetcare.presentation.theme.Navy80
import com.korniykom.webvetcare.presentation.theme.Teal40
import com.korniykom.webvetcare.presentation.theme.Teal70
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LandingPageRoot(
    navigateToLoginScreen: () -> Unit,
    navigateToRegisterScreen: () -> Unit,
    viewModel: LandingPageViewModel = koinViewModel(),
) {
    LandingPageScreen(
        navigateToLoginScreen = navigateToLoginScreen,
        navigateToRegisterScreen = navigateToRegisterScreen,
    )
}

@Composable
fun LandingPageScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToRegisterScreen: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ParticleBackground(
            backgroundGradientColorStart = Navy80,
            backgroundGradientColorEnd = Teal70,
            particlesColor = Teal40.copy(alpha = 0.25f),
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
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 56.sp
                ),
                color = DarkNavy,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Your trusted digital veterinary care platform —\n" +
                        "book appointments, manage health records, connect with doctors.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
                color = DarkNavy,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )
            Spacer(modifier = Modifier.height(28.dp))
            Row {
                WebVetCareButton(
                    text = "Login",
                    onClick = navigateToLoginScreen,
                    style = WebVetCareButtonStyle.TEAL
                )
                Spacer(modifier = Modifier.width(16.dp))
                WebVetCareButton(
                    text = "Register",
                    onClick = navigateToRegisterScreen,
                    style = WebVetCareButtonStyle.WHITE
                )
            }
        }

    }
}