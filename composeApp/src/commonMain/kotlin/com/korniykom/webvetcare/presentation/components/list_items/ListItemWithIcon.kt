package com.korniykom.webvetcare.presentation.components.list_items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ListItemWithIcon(
    showOnlyIcon: Boolean,
    icon: DrawableResource,
    leadingText: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .padding(8.dp)
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(20.dp))
            .animateContentSize()
            .clickable(
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painterResource(icon),
            contentDescription = icon.toString().replace("_", " "),
            colorFilter = ColorFilter.tint(
                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(
                    alpha = .7f
                )
            ),
            modifier = Modifier.size(32.dp)
        )

        AnimatedVisibility(
            visible = !showOnlyIcon,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = leadingText,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isSelected) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground.copy(
                    alpha = .7f
                ),
                modifier = Modifier.padding(start = 24.dp)
            )
        }
    }
}