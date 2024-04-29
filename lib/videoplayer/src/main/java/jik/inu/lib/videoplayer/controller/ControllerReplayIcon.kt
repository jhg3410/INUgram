package jik.inu.lib.videoplayer.controller

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import jik.inu.lib.videoplayer.PlayerIcons.Replay

@Composable
fun ControllerReplayIcon() {
    ControllerIconFrame {
        Icon(
            modifier = it,
            imageVector = Replay,
            contentDescription = "Replay",
            tint = Color.White,
        )
    }
}