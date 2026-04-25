package com.korniykom.webvetcare.presentation.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import webvetcare.front_end.composeapp.generated.resources.Res
import webvetcare.front_end.composeapp.generated.resources.arrow_back
import webvetcare.front_end.composeapp.generated.resources.arrow_forward

@Composable
fun GoBackButton(
    onClick: () -> Unit,
    isExpanded: Boolean = false,
    modifier: Modifier = Modifier,
) {
    IconButton(
        modifier = modifier
            .padding(0.dp),
        colors = IconButtonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 1f),
            disabledContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 1f)
        ),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 16.dp,
            bottomStart = 0.dp,
            bottomEnd = 16.dp
        ),
        onClick = onClick
    ) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = if (isExpanded) {
                    painterResource(Res.drawable.arrow_back)
                } else {
                    painterResource(Res.drawable.arrow_forward)
                },
                contentDescription = "Toggle button",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}


