package jik.inu.core.designsystem.component.navigationbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jik.inu.core.designsystem.component.navigationbar.IGNavigationButtonDefaults.ButtonStrokeBrush
import jik.inu.core.designsystem.icon.IGIcons

@Composable
fun RowScope.IGNavigationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.8f else 1f, label = "")

    Box(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .align(Alignment.CenterVertically)
            .scale(scale = scale)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(100.dp)
            )
            .border(
                width = 2.dp,
                brush = ButtonStrokeBrush,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 18.dp)
                .size(24.dp),
            imageVector = IGIcons.AddRounded,
            contentDescription = "Add Video",
        )
    }
}


private object IGNavigationButtonDefaults {
    val red = Color(0xFFED3737)
    val yellow = Color(0xFFFAFF00)
    val skyBlue = Color(0xFF5EBBFF)

    val ButtonStrokeBrush
        @Composable get() = Brush.verticalGradient(
            listOf(red, yellow, skyBlue)
        )
}

//@Preview
//@Composable
//fun IGNavigationButtonPreview() {
//    IGNavigationButton()
//}