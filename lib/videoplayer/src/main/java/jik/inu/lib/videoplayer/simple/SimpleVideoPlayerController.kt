package jik.inu.lib.videoplayer.simple

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jik.inu.lib.videoplayer.controller.ControllerLoadingWheel
import jik.inu.lib.videoplayer.controller.ControllerPauseIcon
import jik.inu.lib.videoplayer.controller.ControllerPlayIcon
import jik.inu.lib.videoplayer.controller.ControllerReplayIcon

@Composable
internal fun SimpleVideoPlayerController(
    modifier: Modifier = Modifier,
    visible: Boolean,
    playerState: PlayerState,
) {

    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        CenterController(
            modifier = Modifier,
            playerState = playerState
        )
    }
}

@Composable
internal fun CenterController(
    modifier: Modifier = Modifier,
    playerState: PlayerState,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (playerState) {
            PlayerState.PLAYING -> {
                ControllerPlayIcon()
            }

            PlayerState.PAUSED -> {
                ControllerPauseIcon()
            }

            PlayerState.ENDED -> {
                ControllerReplayIcon()
            }

            PlayerState.LOADING -> {
                ControllerLoadingWheel()
            }
        }
    }
}