package jik.inu.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import jik.inu.core.model.Video

@Composable
fun MyVideosScreen(
    modifier: Modifier = Modifier,
    videos: List<Video>,
    onVideoCardClick: (videoId: Int) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        items(items = videos, key = { it.id }) { video ->
            MyVideoCard(
                modifier = Modifier.aspectRatio(118 / 220f),
                thumbnailUrl = video.thumbnail,
                description = video.description,
                onClick = { onVideoCardClick(video.id) }
            )
        }
    }
}


@Composable
private fun MyVideoCard(
    modifier: Modifier = Modifier,
    thumbnailUrl: String,
    description: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = Color.Black)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = thumbnailUrl,
            contentDescription = "Video Thumbnail",
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, bottom = 30.dp),
            text = description,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
        )
    }
}