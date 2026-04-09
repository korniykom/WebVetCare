package com.korniykom.core.presentation.util

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun currentDeviceConfiguration(): DeviceConfiguration {
    val windowSizeClass = calculateWindowSizeClass()
    return DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
}

enum class DeviceConfiguration {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    TABLET_PORTRAIT,
    TABLET_LANDSCAPE,
    DESKTOP;

    companion object {
        fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): DeviceConfiguration {
            return when {
                windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact -> MOBILE_PORTRAIT
                windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact -> MOBILE_LANDSCAPE
                windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium -> TABLET_PORTRAIT
                windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded &&
                        windowSizeClass.heightSizeClass == WindowHeightSizeClass.Medium -> TABLET_LANDSCAPE

                else -> DESKTOP
            }
        }
    }
}