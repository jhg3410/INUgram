package jik.inu.lib.videoplayer.controller

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import jik.inu.lib.videoplayer.PlayerIcons.Replay
import jik.inu.lib.videoplayer.iconSize

@Composable
fun ControllerReplayIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick, modifier = modifier.size(iconSize)) {
        Icon(
            modifier = modifier.fillMaxSize(),
            imageVector = Replay,
            contentDescription = "Replay",
            tint = Color.White,
        )
    }
}