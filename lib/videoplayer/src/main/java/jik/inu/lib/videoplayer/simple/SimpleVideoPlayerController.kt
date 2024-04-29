package jik.inu.lib.videoplayer.simple

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.exoplayer.ExoPlayer
import jik.inu.lib.videoplayer.controller.ControllerPauseIcon
import jik.inu.lib.videoplayer.controller.ControllerPlayIcon
import jik.inu.lib.videoplayer.controller.ControllerReplayIcon

@Composable
internal fun SimpleVideoPlayerController(
    modifier: Modifier = Modifier,
    visible: Boolean,
    playerState: PlayerState,
    player: ExoPlayer,
) {
    val moviePlayer by rememberUpdatedState(newValue = player)

    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier.background(color = Color.Black.copy(alpha = 0.5f))
        ) {
            CenterController(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                playerState = playerState,
                onPlay = moviePlayer::play,
                onPause = moviePlayer::pause,
                onReplay = { moviePlayer.seekTo(0L) },
            )
        }
    }
}

@Composable
fun CenterController(
    modifier: Modifier = Modifier,
    playerState: PlayerState,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onReplay: (Long) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (playerState) {
            PlayerState.PLAYING -> {
                ControllerPauseIcon {
                    onPause()
                }
            }

            PlayerState.PAUSED -> {
                ControllerPlayIcon {
                    onPlay()
                }
            }

            PlayerState.ENDED -> {
                ControllerReplayIcon {
                    onReplay(0L)
                }
            }
        }
    }
}