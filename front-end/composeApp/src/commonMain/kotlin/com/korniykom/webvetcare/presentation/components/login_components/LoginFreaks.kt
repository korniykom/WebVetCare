package com.korniykom.webvetcare.presentation.components.login_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.korniykom.webvetcare.presentation.components.login_components.freaks.RectFreak
import com.korniykom.webvetcare.presentation.components.login_components.freaks.RectFreakInfo
import com.korniykom.webvetcare.presentation.screens.login.FreaksLookState
import com.korniykom.webvetcare.presentation.theme.AlmostRoyal
import com.korniykom.webvetcare.presentation.theme.SatinDeepBlack
import com.korniykom.webvetcare.presentation.theme.UnicornSilver
import com.korniykom.webvetcare.presentation.theme.WhiteAsHeaven

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


        RectFreak(
            modifier = Modifier.fillMaxSize(),
            xOffset = -80f,
            freaksLookState = freaksLookState,
            freakInfo = RectFreakInfo(
                bodyColor = AlmostRoyal,

                topLeftPointX = 200f,
                topLeftPointY = 200f,

                topRightPointX = 0f,
                topRightPointY = 200f,

                bottomLeftPointX = 200f,
                bottomLeftPointY = -200f,

                bottomRightPointX = 0f,
                bottomRightPointY = -200f,

                innerEyeColor = SatinDeepBlack,
                outerEyeColor = WhiteAsHeaven,
                outerRightEyePositionX = 50f,
                outerRightEyePositionY = 40f,
                outerLeftEyePositionX = -50f,
                outerLeftEyePositionY = 40f,

                innerRightEyePositionX = 51f,
                innerRightEyePositionY = 42f,
                innerLeftEyePositionX = -49f,
                innerLeftEyePositionY = 43f
            )
        )

        RectFreak(
            modifier = Modifier.fillMaxSize(),
            yOffset = 100f,
            xOffset = 60f,
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


    }
}

