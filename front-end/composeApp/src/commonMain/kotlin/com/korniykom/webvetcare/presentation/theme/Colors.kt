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
val Transparent = Color(0x00000000)

val Navy10 = Color(0xFF0B1F3A)
val Navy20 = Color(0xFF132D52)
val Navy30 = Color(0xFF1D3D6B)
val Navy40 = Color(0xFF284E87)
val Navy50 = Color(0xFF3660A3)
val Navy60 = Color(0xFF5680BF)
val Navy70 = Color(0xFF7EA0D6)
val Navy80 = Color(0xFFA8C1E9)
val Navy90 = Color(0xFFD0E1F7)
val Navy95 = Color(0xFFE8F0FB)
val Navy99 = Color(0xFFF5F8FE)

val Teal10 = Color(0xFF003733)
val Teal20 = Color(0xFF00504A)
val Teal30 = Color(0xFF006A62)
val Teal40 = Color(0xFF1A7F78)
val Teal50 = Color(0xFF359B94)
val Teal60 = Color(0xFF52B5AF)
val Teal70 = Color(0xFF70D0CA)
val Teal80 = Color(0xFF8EEBE5)
val Teal90 = Color(0xFFB2F5F1)
val Teal95 = Color(0xFFD5FDFB)
val Teal99 = Color(0xFFF0FFFE)

val Amber10 = Color(0xFF331C00)
val Amber20 = Color(0xFF542E00)
val Amber30 = Color(0xFF784200)
val Amber40 = Color(0xFF9E5A00)
val Amber50 = Color(0xFFC67500)
val Amber60 = Color(0xFFF5A623)
val Amber70 = Color(0xFFFFBC57)
val Amber80 = Color(0xFFFFD08A)
val Amber90 = Color(0xFFFFE4BB)
val Amber95 = Color(0xFFFFF1DC)
val Amber99 = Color(0xFFFFFBF5)

val Red10 = Color(0xFF410002)
val Red20 = Color(0xFF690005)
val Red30 = Color(0xFF93000A)
val Red40 = Color(0xFFBA1A1A)
val Red50 = Color(0xFFE53935)
val Red60 = Color(0xFFFF5449)
val Red70 = Color(0xFFFF897D)
val Red80 = Color(0xFFFFB4AB)
val Red90 = Color(0xFFFFDAD6)
val Red95 = Color(0xFFFFEDEA)
val Red99 = Color(0xFFFFFBFF)

val Green10 = Color(0xFF002106)
val Green20 = Color(0xFF00390F)
val Green30 = Color(0xFF00531A)
val Green40 = Color(0xFF006E27)
val Green50 = Color(0xFF43A047)
val Green60 = Color(0xFF5EB661)
val Green70 = Color(0xFF79D27C)
val Green80 = Color(0xFF95EF98)
val Green90 = Color(0xFFB7FFB8)
val Green95 = Color(0xFFDAFFD9)
val Green99 = Color(0xFFF4FFF3)

val Neutral10 = Color(0xFF1A1C1E)
val Neutral20 = Color(0xFF2F3033)
val Neutral30 = Color(0xFF45474A)
val Neutral40 = Color(0xFF5D5E62)
val Neutral50 = Color(0xFF767679)
val Neutral60 = Color(0xFF909094)
val Neutral70 = Color(0xFFABABAF)
val Neutral80 = Color(0xFFC7C6CA)
val Neutral90 = Color(0xFFE3E2E6)
val Neutral95 = Color(0xFFF1F0F4)
val Neutral99 = Color(0xFFFFFBFF)

val NeutralVar10 = Color(0xFF181C22)
val NeutralVar20 = Color(0xFF2C3038)
val NeutralVar30 = Color(0xFF43474F)
val NeutralVar40 = Color(0xFF5B5E67)
val NeutralVar50 = Color(0xFF747780)
val NeutralVar60 = Color(0xFF8E9099)
val NeutralVar70 = Color(0xFFA8ABB4)
val NeutralVar80 = Color(0xFFC4C6CF)
val NeutralVar90 = Color(0xFFE0E2EC)
val NeutralVar95 = Color(0xFFEEF0FA)
val NeutralVar99 = Color(0xFFFFFBFF)

val LocalExtendedColors = staticCompositionLocalOf { lightExtendedColors }

val ColorScheme.extended: ExtendedColors
    @ReadOnlyComposable
    @Composable
    get() = LocalExtendedColors.current

@Immutable
data class ExtendedColors(
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val onWarning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
    val transparent: Color,
)

val lightColorScheme = lightColorScheme(
    primary = Teal40,
    onPrimary = White,
    primaryContainer = Teal90,
    onPrimaryContainer = Teal10,
    secondary = Navy40,
    onSecondary = White,
    secondaryContainer = Navy90,
    onSecondaryContainer = Navy10,
    tertiary = Amber60,
    onTertiary = Amber10,
    tertiaryContainer = Amber90,
    onTertiaryContainer = Amber10,
    error = Red50,
    onError = White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Neutral99,
    onBackground = Neutral10,
    surface = Neutral99,
    onSurface = Neutral10,
    surfaceVariant = NeutralVar90,
    onSurfaceVariant = NeutralVar30,
    outline = NeutralVar50,
    outlineVariant = NeutralVar80,
    scrim = Neutral10,
    inverseSurface = Neutral20,
    inverseOnSurface = Neutral95,
    inversePrimary = Teal80,
    surfaceTint = Teal40,
)

val darkColorScheme = darkColorScheme(
    primary = Teal80,
    onPrimary = Teal20,
    primaryContainer = Teal30,
    onPrimaryContainer = Teal90,
    secondary = Navy80,
    onSecondary = Navy20,
    secondaryContainer = Navy30,
    onSecondaryContainer = Navy90,
    tertiary = Amber80,
    onTertiary = Amber20,
    tertiaryContainer = Amber30,
    onTertiaryContainer = Amber90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Neutral10,
    onBackground = Neutral90,
    surface = Neutral10,
    onSurface = Neutral90,
    surfaceVariant = NeutralVar30,
    onSurfaceVariant = NeutralVar80,
    outline = NeutralVar60,
    outlineVariant = NeutralVar30,
    scrim = Neutral10,
    inverseSurface = Neutral90,
    inverseOnSurface = Neutral20,
    inversePrimary = Teal40,
    surfaceTint = Teal80,
)

val lightExtendedColors = ExtendedColors(
    success = Green50,
    onSuccess = White,
    successContainer = Green90,
    onSuccessContainer = Green10,
    warning = Amber60,
    onWarning = Amber10,
    warningContainer = Amber90,
    onWarningContainer = Amber10,
    transparent = Transparent,
)

val darkExtendedColors = ExtendedColors(
    success = Green80,
    onSuccess = Green20,
    successContainer = Green30,
    onSuccessContainer = Green90,
    warning = Amber80,
    onWarning = Amber20,
    warningContainer = Amber30,
    onWarningContainer = Amber90,
    transparent = Transparent,
)