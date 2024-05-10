package jik.inu.feature.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import jik.inu.core.designsystem.component.navigationbar.NavigationBarTheme
import jik.inu.core.designsystem.icon.IGIcons
import jik.inu.core.designsystem.theme.Blue50
import jik.inu.lib.videoplayer.simple.SimpleVideoPlayer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    changeNavigationBarTheme: (NavigationBarTheme) -> Unit
) {
    val videos by homeViewModel.videos.collectAsStateWithLifecycle()
    var preIdx by remember { mutableIntStateOf(0) }

    val pagerState = rememberPagerState(pageCount = { videos.size })
    if (pagerState.currentPageOffsetFraction == 0f) {
        preIdx = pagerState.currentPage
    }

    DisposableEffect(key1 = Unit) {
        changeNavigationBarTheme(NavigationBarTheme.Dark)

        onDispose {
            changeNavigationBarTheme(NavigationBarTheme.Light)
        }
    }

    VerticalPager(
        modifier = modifier,
        state = pagerState,
    ) { index ->
        if (index == preIdx) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            ) {
                SimpleVideoPlayer(contentUri = videos[index].url.toUri()) {
                    VideoThumbnail(thumbnailUrl = videos[index].thumbnail)
                }
                VideoInteractionOverlay(description = videos[index].description)
            }
        } else {
            VideoThumbnail(thumbnailUrl = videos[index].thumbnail)
        }
    }
}

@Composable
private fun VideoThumbnail(
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

@Composable
private fun VideoInteractionOverlay(
    modifier: Modifier = Modifier,
    description: String
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
            .padding(
                vertical = 60.dp,
                horizontal = 20.dp
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 20.dp),
            text = description,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            Log.d("HomeScreen", "Favorite Clicked")
                        },
                    imageVector = IGIcons.Favorite,
                    contentDescription = "Favorite",
                    tint = Blue50
                )
                Text(
                    text = "좋아요",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .graphicsLayer {
                            scaleX = -1f
                        },
                    imageVector = IGIcons.Reply,
                    contentDescription = "Share",
                    tint = Color.White
                )
                Text(
                    text = "공유",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}