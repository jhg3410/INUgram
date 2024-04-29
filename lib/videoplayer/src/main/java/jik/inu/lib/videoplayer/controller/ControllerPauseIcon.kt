package jik.inu.lib.videoplayer.controller

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import jik.inu.lib.videoplayer.PlayerIcons.Pause

@Composable
fun ControllerPauseIcon() {
    ControllerIconFrame {
        Icon(
            modifier = it,
            imageVector = Pause,
            contentDescription = "Pause",
            tint = Color.White,
        )
    }
}