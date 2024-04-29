package jik.inu.lib.videoplayer.simple

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
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


/**
    just play and stop player
 */
@Composable
fun SimpleVideoPlayer(
    modifier: Modifier = Modifier,
    contentUri: Uri
) {
    val context = LocalContext.current

    var player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(contentUri))
            playWhenReady = false
            prepare()
        }
    }
    var controllerVisible by remember { mutableStateOf(true) }
    val playerState by remember { mutableStateOf(PlayerState.PAUSED) }

    fun releasePlayer() {
        player.release()
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
            onScreenClick = { controllerVisible = !controllerVisible }
        )
        SimpleVideoPlayerController(
            modifier = Modifier,
            visible = controllerVisible,
            playerState = playerState,
            player = player
        )
    }

}


enum class PlayerState {
    PLAYING, PAUSED, ENDED
}