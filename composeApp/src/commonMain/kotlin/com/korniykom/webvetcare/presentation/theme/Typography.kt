package com.korniykom.webvetcare.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import webvetcare.composeapp.generated.resources.*

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

val Typography.H0: TextStyle
    @Composable get() = TextStyle(
        fontFamily = DMSans,
        fontWeight = FontWeight.Bold,
        fontSize = 52.sp,
    )
val Typography.H1: TextStyle
    @Composable get() = TextStyle(
        fontFamily = DMSans,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    )
val Typography.H2: TextStyle
    @Composable get() = TextStyle(
        fontFamily = DMSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    )
val Typography.H3: TextStyle
    @Composable get() = TextStyle(
        fontFamily = DMSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    )
val Typography.bodyRegular: TextStyle
    @Composable get() = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
val Typography.bodyBold: TextStyle
    @Composable get() = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    )
val Typography.caption: TextStyle
    @Composable get() = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
    )
val Typography.label: TextStyle
    @Composable get() = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
    )
