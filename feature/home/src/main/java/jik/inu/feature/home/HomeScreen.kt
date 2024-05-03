package jik.inu.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import jik.inu.lib.videoplayer.simple.SimpleVideoPlayer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val videos by homeViewModel.videos.collectAsStateWithLifecycle()
    var preIdx by remember { mutableIntStateOf(0) }

    val pagerState = rememberPagerState(pageCount = { videos.size })
    if (pagerState.currentPageOffsetFraction == 0f) {
        preIdx = pagerState.currentPage
    }

    VerticalPager(
        modifier = modifier,
        state = pagerState,
        key = { videos[it].url }
    ) { index ->
        if (index == preIdx) {
            SimpleVideoPlayer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black),
                contentUri = videos[index].url.toUri()
            )
        } else {
            VideoThumbnail()
        }
    }
}

@Composable
private fun VideoThumbnail(
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500/sLD3mTo4Fck1UxbJWLrjaTnwwFl.jpg",
        contentDescription = "Video Thumbnail",
        modifier = modifier.fillMaxSize(),
    )
}