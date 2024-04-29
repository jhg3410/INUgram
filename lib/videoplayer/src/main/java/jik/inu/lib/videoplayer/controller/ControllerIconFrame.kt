package jik.inu.lib.videoplayer.controller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun ControllerIconFrame(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = Color.Black.copy(alpha = 0.5f),
                shape = CircleShape
            )
            .padding(all = 10.dp)
    ) {
        content(Modifier.size(40.dp))
    }
}