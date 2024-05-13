package jik.inu.lib.videoplayer.shorts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage

@Composable
fun ShortsThumbnail(
    modifier: Modifier = Modifier,
    thumbnailUrl: String
) {
    AsyncImage(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black),
        model = thumbnailUrl,
        contentDescription = "Video Thumbnail",
    )
}