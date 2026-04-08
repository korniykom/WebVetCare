package com.korniykom.webvetcare.presentation.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.korniykom.core.presentation.util.DeviceConfiguration
import com.korniykom.core.presentation.util.currentDeviceConfiguration

@Composable
fun AdaptiveFormLayout(
    modifier: Modifier = Modifier
) {
    val configuration = currentDeviceConfiguration()

    when(configuration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> TODO()
        DeviceConfiguration.MOBILE_LANDSCAPE -> TODO()
        DeviceConfiguration.TABLET_PORTRAIT -> TODO()
        DeviceConfiguration.TABLET_LANDSCAPE -> TODO()
        DeviceConfiguration.DESKTOP -> {

        }
    }
}