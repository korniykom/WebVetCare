package com.korniykom.webvetcare.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import webvetcare.composeapp.generated.resources.DMSans_Bold
import webvetcare.composeapp.generated.resources.DMSans_Regular
import webvetcare.composeapp.generated.resources.DMSans_SemiBold
import webvetcare.composeapp.generated.resources.Lato_Bold
import webvetcare.composeapp.generated.resources.Lato_Regular
import webvetcare.composeapp.generated.resources.Res

val DMSans
    @Composable get() = FontFamily(
        Font(
            resource = Res.font.DMSans_Bold,
            weight = FontWeight.Bold
        ),
        Font(
            resource = Res.font.DMSans_Regular,
            weight = FontWeight.Normal
        ),
        Font(
            resource = Res.font.DMSans_SemiBold,
            weight = FontWeight.SemiBold
        )
    )

val Lato
    @Composable get() = FontFamily(
        Font(
            resource = Res.font.Lato_Bold,
            weight = FontWeight.Bold
        ),
        Font(
            resource = Res.font.Lato_Regular,
            weight = FontWeight.Normal
        )
    )

@Composable
fun AppTypography(): Typography {
    val dmSans = DMSans
    val lato = Lato

    return Typography(
        displayLarge = TextStyle(
            fontFamily = dmSans,
            fontWeight = FontWeight.Bold,
            fontSize = 57.sp
        ),
        displayMedium = TextStyle(
            fontFamily = dmSans,
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp
        ),
        displaySmall = TextStyle(
            fontFamily = dmSans,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp
        ),

        headlineLarge = TextStyle(
            fontFamily = dmSans,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = dmSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = dmSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        ),

        titleLarge = TextStyle(
            fontFamily = dmSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp
        ),
        titleMedium = TextStyle(
            fontFamily = dmSans,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),
        titleSmall = TextStyle(
            fontFamily = dmSans,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),

        bodyLarge = TextStyle(
            fontFamily = lato,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = lato,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        bodySmall = TextStyle(
            fontFamily = lato,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),

        labelLarge = TextStyle(
            fontFamily = lato,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        ),
        labelMedium = TextStyle(
            fontFamily = lato,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        ),
        labelSmall = TextStyle(
            fontFamily = lato,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp
        )
    )
}
