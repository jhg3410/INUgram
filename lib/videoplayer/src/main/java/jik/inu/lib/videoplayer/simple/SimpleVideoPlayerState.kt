package jik.inu.lib.videoplayer.simple

import androidx.media3.exoplayer.ExoPlayer

internal enum class PlayerState {
    PLAYING, PAUSED, ENDED, LOADING
}

internal fun getControllerState(
    isPlaying: Boolean,
    playbackState: Int
): PlayerState {
    if (isPlaying) {
        return PlayerState.PLAYING
    }
    if (playbackState == ExoPlayer.STATE_READY) {
        return PlayerState.PAUSED
    }
    if (playbackState == ExoPlayer.STATE_ENDED) {
        return PlayerState.ENDED
    }

    return PlayerState.LOADING
}