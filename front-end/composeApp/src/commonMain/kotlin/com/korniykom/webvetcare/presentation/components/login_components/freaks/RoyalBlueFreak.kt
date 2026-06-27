//package com.korniykom.webvetcare.presentation.components.login_components
//
//import androidx.compose.foundation.Canvas
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Path
//import androidx.compose.ui.graphics.drawscope.DrawScope
//import com.korniykom.webvetcare.presentation.theme.AlmostRoyal
//import com.korniykom.webvetcare.presentation.theme.SatinDeepBlack
//
//@Composable
//fun RoyalBlueFreak(
//    modifier: Modifier = Modifier,
//    bodyColor: Color = AlmostRoyal,
//    eyeColor: Color = SatinDeepBlack,
//    mouthColor: Color = SatinDeepBlack,
//    xOffset: Int = 0,
//    yOffset: Int = 0,
//    ) {
//
//    Canvas(modifier = modifier) {
//        val centerX = size.width / 2 + xOffset
//        val centerY = size.height / 2 + yOffset
//
//        DrawBody(centerX, centerY, bodyColor)
//
//        DrawLeftEye(centerX, centerY, eyeColor)
//
//        DrawRightEye(centerX, centerY, eyeColor)
//
//        DrawMouth(centerX, centerY, mouthColor)
//    }
//}
//
//private fun DrawScope.DrawBody(centerX: Float, centerY: Float, color: Color) {
//    val bodyPath = Path().apply {
//        moveTo(centerX - 100, centerY -200)
//        lineTo(centerX + 100f, centerY - 200)
//        lineTo(centerX + 100f, centerY + 100f)
//        lineTo(centerX - 100f, centerY + 100f)
//        close()
//    }
//    drawPath(bodyPath, color = color)
//}
//
//private fun DrawScope.DrawLeftEye(centerX: Float, centerY: Float, color: Color) {
//    drawCircle(
//        color = color,
//        radius = 6f,
//        center = Offset(x = centerX - 10f, y = centerY - 125f)
//    )
//}
//
//private fun DrawScope.DrawRightEye(centerX: Float, centerY: Float, color: Color) {
//    drawCircle(
//        color = color,
//        radius = 6f,
//        center = Offset(x = centerX + 30f, y = centerY - 130f)
//    )
//}
//
//private fun DrawScope.DrawMouth(centerX: Float, centerY: Float, color: Color) {
//    drawArc(
//        color = color,
//        topLeft = Offset(centerX - 10f, centerY - 100f),
//        size = Size(20f, 17f),
//        startAngle = 16f,
//        sweepAngle = 180f,
//        useCenter = false,
//    )
//}