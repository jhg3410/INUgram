package jik.inu.core.designsystem.component.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import jik.inu.core.designsystem.theme.Blue50


internal object IGTextFieldDefaults {

    val textActiveColor = Blue50
    val textDefaultColor = Color(0xFF767676)

    val dividerActiveColor = Blue50
    val dividerDefaultColor = Color(0xFFD9D9D9)


    @Composable
    fun animatedColor(targetColor: Color) = animateColorAsState(
        targetValue = targetColor,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "Animated Color"
    ).value


    @Composable
    fun textAnimatedScale(targetValue: Float) = animateFloatAsState(
        targetValue = targetValue,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "textAnimatedScale",
    ).value


    @Composable
    fun textAnimatedPosition(targetValue: IntOffset) = animateIntOffsetAsState(
        targetValue = targetValue,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "textAnimatedPosition",
    ).value

}