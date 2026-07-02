package com.korniykom.webvetcare.presentation.components.login_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.korniykom.webvetcare.presentation.components.login_components.freaks.*
import com.korniykom.webvetcare.presentation.screens.login.FreaksLookState
import com.korniykom.webvetcare.presentation.theme.*

@Composable
fun LoginFreaks(
    modifier: Modifier = Modifier,
    freaksLookState: FreaksLookState,
) {

    Box(
        modifier = modifier
            .background(
                color = UnicornSilver,
                shape = RoundedCornerShape(
                    topStart = 32.dp,
                    bottomStart = 32.dp,
                    topEnd = 0.dp,
                    bottomEnd = 0.dp
                )

            ),

        ) {


        AlmostRoyalFreak(
            modifier = Modifier.fillMaxSize(),
            xOffset = -90f,
            freaksLookState = freaksLookState,
            freakInfo = RectFreakInfo(
                bodyColor = AlmostRoyal,

                topLeftPointX = 160f,
                topLeftPointY = 200f,

                topRightPointX = 0f,
                topRightPointY = 200f,

                bottomLeftPointX = 160f,
                bottomLeftPointY = -200f,

                bottomRightPointX = 0f,
                bottomRightPointY = -200f,

                innerEyeColor = SatinDeepBlack,
                outerEyeColor = WhiteAsHeaven,
                outerRightEyePositionX = 50f,
                outerRightEyePositionY = 40f,
                outerLeftEyePositionX = -50f,
                outerLeftEyePositionY = 40f,

                innerRightEyePositionX = 50f,
                innerRightEyePositionY = 40f,
                innerLeftEyePositionX = -50f,
                innerLeftEyePositionY = 40f
            )
        )







        OrangeSemiCircle(
            modifier = Modifier.fillMaxSize(),
            freaksLookState = freaksLookState,
            freakInfo = SemiCircleInfo(
                xScaleOffset = 1f,
                yScaleOffset = 1f,
                radius = 150f,
                xOffset = -190f,
                yOffset = 25f,
                color = BurningTrail,
                outerEyeColor = WhiteAsHeaven,
                innerEyeColor = SatinDeepBlack
            ),
        )

        SatinBlackRectFreak(
            modifier = Modifier.fillMaxSize(),
            yOffset = 100f,
            xOffset = 20f,
            freaksLookState = freaksLookState,
            freakInfo = RectFreakInfo(
                bodyColor = SatinDeepBlack,

                topLeftPointX = 100f,
                topLeftPointY = 100f,

                topRightPointX = 0f,
                topRightPointY = 100f,

                bottomLeftPointX = 100f,
                bottomLeftPointY = -100f,

                bottomRightPointX = 0f,
                bottomRightPointY = -100f,

                innerEyeColor = SatinDeepBlack,
                outerEyeColor = WhiteAsHeaven,
                outerRightEyePositionX = 20f,
                outerRightEyePositionY = 20f,
                outerLeftEyePositionX = -40f,
                outerLeftEyePositionY = 20f,

                innerRightEyePositionX = 21f,
                innerRightEyePositionY = 21f,
                innerLeftEyePositionX = -39f,
                innerLeftEyePositionY = 21f,
            )
        )

        TealSemiCircle(
            modifier = Modifier.fillMaxSize(),
            freaksLookState = freaksLookState,
            freakInfo = SemiCircleInfo(
                xScaleOffset = 1f,
                yScaleOffset = 1f,
                radius = 100f,
                xOffset = 60f,
                yOffset = 50f,
                color = Teal70,

                outerEyeColor = WhiteAsHeaven,
                innerEyeColor = SatinDeepBlack
            ),
        )
    }
}

