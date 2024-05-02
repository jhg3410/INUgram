package jik.inu.lib.videoplayer.simple

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import jik.inu.lib.videoplayer.VideoPlayerScreen
import jik.inu.lib.videoplayer.listener.VideoPlayerListener.stateChangedListener
import kotlinx.coroutines.delay


/**
just play and stop player

@param isAutoReplay: if true, the video will be replayed automatically when it ends
 */
@Composable
fun SimpleVideoPlayer(
    modifier: Modifier = Modifier,
    isAutoReplay: Boolean = false,
    contentUri: Uri
) {
    val context = LocalContext.current
    var controllerVisible by remember { mutableStateOf(false) }
    var playerState by remember { mutableStateOf(PlayerState.PLAYING) }

    val stateChangedListener = stateChangedListener { changedPlayer ->
        playerState = getControllerState(
            isPlaying = changedPlayer.isPlaying,
            playbackState = changedPlayer.playbackState
        )
    }

    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = if (isAutoReplay) ExoPlayer.REPEAT_MODE_ONE else ExoPlayer.REPEAT_MODE_OFF
            addListener(stateChangedListener)
            setMediaItem(MediaItem.fromUri(contentUri))
            playWhenReady = true
            prepare()
        }
    }

    fun releasePlayer() {
        player.removeListener(stateChangedListener)
        player.release()
    }

    LaunchedEffect(key1 = playerState) {
        if (playerState != PlayerState.ENDED) {
            if (controllerVisible) {
                delay(1_000)
                controllerVisible = false
            }
        } else {
            controllerVisible = true
        }
    }

    LifecycleStartEffect {
        onStopOrDispose {
            releasePlayer()
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        VideoPlayerScreen(
            player = player,
            onScreenClick = {
                if (player.isPlaying) {
                    player.pause()
                } else if (
                    playerState == PlayerState.ENDED
                ) {
                    player.seekTo(0)
                } else {
                    player.play()
                }
                controllerVisible = true
            }
        )
        SimpleVideoPlayerController(
            visible = controllerVisible,
            playerState = playerState,
        )
    }
}