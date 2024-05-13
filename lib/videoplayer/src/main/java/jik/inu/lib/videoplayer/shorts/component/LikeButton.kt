package jik.inu.lib.videoplayer.shorts.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun LikeButton(
    modifier: Modifier = Modifier,
    liked: Boolean,
    likeImageVector: ImageVector = Icons.Filled.Favorite,
    likeTint: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,
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
        if (liked) HeartEffect(likeImageVector = likeImageVector)
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
            imageVector = likeImageVector,
            contentDescription = "Favorite",
            tint = if (liked) likeTint else Color.White
        )
    }
}

@Composable
private fun HeartEffect(
    modifier: Modifier = Modifier,
    likeImageVector: ImageVector,
) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = Unit) {
        visible = false
    }

    val offsetValue: @Composable (duration: Int, offsetY: Int) -> State<IntOffset> =
        { duration, offsetY ->
            animateIntOffsetAsState(
                targetValue = if (visible) IntOffset.Zero else IntOffset(x = 0, y = offsetY),
                animationSpec = tween(durationMillis = duration),
                label = "offsetValue"
            )
        }

    val alphaValue: @Composable (duration: Int) -> State<Float> = { duration ->
        animateFloatAsState(
            targetValue = if (visible) 1.0f else 0f,
            animationSpec = tween(durationMillis = duration),
            label = "alphaValue"
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        val redOffsetValue = offsetValue(1300, -20).value
        val redAlphaValue = alphaValue(1300).value
        Icon(
            modifier = Modifier
                .offset {
                    redOffsetValue.copy(
                        x = redOffsetValue.x - 7.dp.roundToPx(),
                        y = redOffsetValue.y - 3.dp.roundToPx()
                    )
                }
                .graphicsLayer { alpha = redAlphaValue }
                .size(size = 10.dp),
            imageVector = likeImageVector,
            contentDescription = "Favorite",
            tint = Color(0xFFED3737)
        )

        val greenOffsetValue = offsetValue(1000, -18).value
        val greenAlphaValue = alphaValue(1000).value
        Icon(
            modifier = Modifier
                .offset {
                    greenOffsetValue.copy(
                        x = greenOffsetValue.x + 4.dp.roundToPx(),
                        y = greenOffsetValue.y - 10.dp.roundToPx()
                    )
                }
                .graphicsLayer { alpha = greenAlphaValue }
                .size(size = 17.dp),
            imageVector = likeImageVector,
            contentDescription = "Favorite",
            tint = Color(0xFF37ED6A)
        )

        val yellowOffsetValue = offsetValue(700, -16).value
        val yellowAlphaValue = alphaValue(700).value
        Icon(
            modifier = Modifier
                .offset {
                    yellowOffsetValue.copy(
                        x = yellowOffsetValue.x - 4.dp.roundToPx(),
                        y = yellowOffsetValue.y - 27.dp.roundToPx()
                    )
                }
                .graphicsLayer { alpha = yellowAlphaValue }
                .size(size = 12.dp),
            imageVector = likeImageVector,
            contentDescription = "Favorite",
            tint = Color(0xFFE9ED37)
        )
    }
}