package com.korniykom.webvetcare.presentation.components.login_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.korniykom.webvetcare.presentation.components.login_components.freaks.SquareFreakBodyCorners
import com.korniykom.webvetcare.presentation.screens.login.FreaksLookState
import com.korniykom.webvetcare.presentation.theme.UnicornSilver

@Composable
fun LoginFreaks(
    modifier: Modifier = Modifier,
    freaksLookState: FreaksLookState,
) {

    Box(
        modifier = modifier
            .background(
                color = UnicornSilver,
                shape = RoundedCornerShape(
                    topStart = 32.dp,
                    bottomStart = 32.dp,
                    topEnd = 0.dp,
                    bottomEnd = 0.dp
                )

            ),

    ) {
        SquareFreak(
            modifier = Modifier.fillMaxSize(),
            yOffset = 60,
            freaksLookState = freaksLookState,
            bodyPoints = SquareFreakBodyCorners(
                0f, 0f,
                100f, 0f,
                0f, -240f,
                100f, -240f
            )
        )

    }
}

