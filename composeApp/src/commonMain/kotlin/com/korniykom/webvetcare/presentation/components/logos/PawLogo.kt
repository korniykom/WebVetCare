package com.korniykom.webvetcare.presentation.components.logos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import webvetcare.composeapp.generated.resources.Res
import webvetcare.composeapp.generated.resources.paw

@Composable
fun PawLogo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Image(
            painterResource(Res.drawable.paw),
            contentDescription = "Logo depicting paw",
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
        )
    }
}