package jik.inu.feature.home.component

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import jik.inu.core.designsystem.icon.IGIcons
import jik.inu.core.designsystem.theme.Blue50

@Composable
fun LikeButton(
    modifier: Modifier = Modifier,
    liked: Boolean,
    onClick: () -> Unit
) {

    val scaleAnimation = remember { Animatable(0f) }

    LaunchedEffect(key1 = liked) {
        if (liked) {
            scaleAnimation.animateTo(targetValue = 1.4f)
            scaleAnimation.animateTo(targetValue = 1f)
        }
    }

    Icon(
        modifier = modifier
            .size(40.dp)
            .clickable {
                onClick()
            }
            .graphicsLayer {
                scaleX = if (liked.not()) 1f else scaleAnimation.value
                scaleY = if (liked.not()) 1f else scaleAnimation.value
            },
        imageVector = IGIcons.Favorite,
        contentDescription = "Favorite",
        tint = if (liked) Blue50 else Color.White
    )
}