package jik.inu.lib.videoplayer.controller

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import jik.inu.lib.videoplayer.PlayerIcons.Play

@Composable
fun ControllerPlayIcon() {
    ControllerIconFrame {
        Icon(
            modifier = it,
            imageVector = Play,
            contentDescription = "Pause",
            tint = Color.White,
        )
    }
}