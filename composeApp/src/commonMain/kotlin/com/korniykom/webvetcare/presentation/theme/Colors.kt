package com.korniykom.webvetcare.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val DarkNavy = Color(0xFF0B1F3A)
val Teal = Color(0xFF1A7F78)
val White = Color(0xFFF8FAFC)
val LightGray = Color(0xFFE8EDF2)
val Amber = Color(0xFFF5A623)
val ErrorRed = Color(0xFFE53935)
val Success = Color(0xFF43A047)
val Transparent = Color(0x000)

val LocalExtendedColors = staticCompositionLocalOf { lightExtendedColors }

val ColorScheme.extended: ExtendedColors
    @ReadOnlyComposable
    @Composable
    get() = LocalExtendedColors.current

@Immutable
data class ExtendedColors(
    val success: Color,
    val transparent: Color
)

val lightColorScheme = lightColorScheme(
    primary = Teal,
    onPrimary = White,
    primaryContainer = LightGray,
    onPrimaryContainer = Teal,
    secondary = DarkNavy,
    onSecondary = White,
    tertiary = Amber,
    onTertiary = DarkNavy,
    error = ErrorRed,
    onError = White,
    background = White,
    surface = White,
    onSurface = DarkNavy,
    surfaceVariant = LightGray,
    onSurfaceVariant = LightGray
)

val darkColorScheme = darkColorScheme(
    primary = Teal,
    onPrimary = White,
    primaryContainer = LightGray,
    onPrimaryContainer = Teal,
    secondary = DarkNavy,
    onSecondary = White,
    tertiary = Amber,
    onTertiary = DarkNavy,
    error = ErrorRed,
    onError = White,
    background = White,
    surface = White,
    onSurface = DarkNavy,
    surfaceVariant = LightGray,
    onSurfaceVariant = LightGray
)

val lightExtendedColors = ExtendedColors(
    success = Success,
    transparent = Transparent
)

val darkExtendedColors = ExtendedColors(
    success = Success,
    transparent = Transparent

)