package jik.inu.feature.home.component

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (liked) HeartEffect()
        Icon(
            modifier = Modifier
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
}

@Composable
private fun HeartEffect(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Icon(
            modifier = Modifier
                .offset(x = (-7).dp, y = (-3).dp)
                .size(size = 10.dp),
            imageVector = IGIcons.Favorite,
            contentDescription = "Favorite",
            tint = Color(0xFFED3737)
        )
        Icon(
            modifier = Modifier
                .offset(x = 4.dp, y = (-10).dp)
                .size(size = 17.dp),
            imageVector = IGIcons.Favorite,
            contentDescription = "Favorite",
            tint = Color(0xFF37ED6A)
        )
        Icon(
            modifier = Modifier
                .offset(x = (-4).dp, y = (-27).dp)
                .size(size = 12.dp),
            imageVector = IGIcons.Favorite,
            contentDescription = "Favorite",
            tint = Color(0xFFE9ED37)
        )
    }
}