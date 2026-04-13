package com.korniykom.webvetcare.presentation.components.list_items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Start
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.korniykom.webvetcare.presentation.theme.Teal30
import com.korniykom.webvetcare.presentation.theme.Teal40
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ListItemWithIcon(
    showOnlyIcon: Boolean,
    icon: DrawableResource,
    leadingText: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .heightIn(min = 48.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painterResource(icon),
            contentDescription = icon.toString().replace("_", " "),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground.copy(alpha = .7f)),
            modifier = Modifier.size(32.dp)

        )

        AnimatedVisibility(!showOnlyIcon){
            Text(
                text = leadingText,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .7f),
                modifier = Modifier.padding(start = 24.dp)
            )
        }
    }
}