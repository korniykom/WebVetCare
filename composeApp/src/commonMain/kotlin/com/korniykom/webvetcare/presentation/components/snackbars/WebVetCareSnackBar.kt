package com.korniykom.webvetcare.presentation.components.snackbars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.korniykom.webvetcare.presentation.theme.extended
import org.jetbrains.compose.resources.painterResource
import webvetcare.composeapp.generated.resources.Close_round
import webvetcare.composeapp.generated.resources.Done_round
import webvetcare.composeapp.generated.resources.Res

enum class SnackBarType {
    ERROR, SUCCESS
}

class TypedSnackBarVisuals(
    override val message: String,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = true,
    val type: SnackBarType
) : SnackbarVisuals

suspend fun SnackbarHostState.show(
    message: String,
    type: SnackBarType
) = showSnackbar(TypedSnackBarVisuals(message = message, type = type))

@Composable
fun WebVetCareSnackBar(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(hostState = hostState) { data ->
        val type = (data.visuals as? TypedSnackBarVisuals)?.type ?: SnackBarType.SUCCESS

        val containerColor =
            if (type == SnackBarType.SUCCESS) MaterialTheme.colorScheme.extended.onSuccessContainer else MaterialTheme.colorScheme.errorContainer
        val contentColor =
            if (type == SnackBarType.SUCCESS) MaterialTheme.colorScheme.extended.onSuccess else MaterialTheme.colorScheme.onErrorContainer
        val icon =
            if (type == SnackBarType.SUCCESS) Res.drawable.Done_round else Res.drawable.Close_round

        Snackbar(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            containerColor = containerColor,
            contentColor = contentColor,
            dismissActionContentColor = contentColor.copy(alpha = 0.7f),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(icon), contentDescription = null, tint = contentColor)
                Text(data.visuals.message, color = contentColor)
            }
        }
    }
}