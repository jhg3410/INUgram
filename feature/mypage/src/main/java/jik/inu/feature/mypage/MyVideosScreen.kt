package jik.inu.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MyVideosScreen(
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        items(30) {
            MyVideoCard()
        }
    }
}


@Composable
private fun MyVideoCard(
    modifier: Modifier = Modifier,
    thumbnailUrl: String = "https://image.tmdb.org/t/p/w500/2lBOQK06tltt8SQaswgb8d657Mv.jpg",
) {
    Box(
        modifier = modifier
            .background(color = Color.Black)
            .aspectRatio(118 / 220f),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = thumbnailUrl,
            contentDescription = "Video Thumbnail",
        )
    }
}