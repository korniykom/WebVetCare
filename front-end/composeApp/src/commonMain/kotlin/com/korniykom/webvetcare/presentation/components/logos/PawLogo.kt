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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import webvetcare.front_end.composeapp.generated.resources.Res
import webvetcare.front_end.composeapp.generated.resources.paw

@Composable
fun PawLogo(
    imagePadding: Dp = 16.dp,
    imageSize: Dp = 32.dp,
    boxCornerSize: Dp = 24.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(boxCornerSize))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Image(
            painterResource(Res.drawable.paw),
            contentDescription = "Logo depicting paw",
            modifier = Modifier
                .padding(imagePadding)
                .size(imageSize),
        )
    }
}