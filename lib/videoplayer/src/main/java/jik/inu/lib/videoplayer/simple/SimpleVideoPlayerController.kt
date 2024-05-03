package jik.inu.lib.videoplayer.simple

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun SimpleVideoPlayerController(
    modifier: Modifier = Modifier,
    visible: Boolean,
    controllerSymbol: @Composable () -> Unit
) {

    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        CenterController(
            modifier = Modifier,
            controllerSymbol = controllerSymbol
        )
    }
}

@Composable
internal fun CenterController(
    modifier: Modifier = Modifier,
    controllerSymbol: @Composable () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        controllerSymbol()
    }
}