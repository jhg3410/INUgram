package jik.inu.lib.videoplayer.shorts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.net.toUri
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jik.inu.lib.videoplayer.simple.SimpleVideoPlayer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Shorts(
    modifier: Modifier = Modifier,
    playList: List<ShortsVideo>,
    initialVideoPage: Int = 0,
    likedVideoIds: List<Int>,
    onLikeClicked: (id: Int) -> Unit,
    onShareClicked: (id: Int) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()
    var preIdx by remember { mutableIntStateOf(0) }

    val pagerState = rememberPagerState(
        initialPage = initialVideoPage,
        pageCount = { playList.size }
    )
    if (pagerState.currentPageOffsetFraction == 0f) {
        preIdx = pagerState.currentPage
    }

    DisposableEffect(key1 = Unit) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )

        onDispose {
            if (isDarkTheme.not()) {
                systemUiController.setStatusBarColor(
                    color = Color.Transparent,
                    darkIcons = true
                )
            }
        }
    }

    VerticalPager(
        modifier = modifier,
        state = pagerState,
        key = { playList[it].id }
    ) { index ->
        if (index == preIdx) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            ) {
                SimpleVideoPlayer(
                    contentUri = playList[index].videoUrl.toUri(),
                    isAutoReplay = true
                ) {
                    ShortsThumbnail(thumbnailUrl = playList[index].thumbnailUrl)
                }
                ShortsOverlay(
                    description = playList[index].description,
                    liked = likedVideoIds.contains(playList[index].id),
                    onLikeClicked = { onLikeClicked(playList[index].id) },
                    onShareClicked = { onShareClicked(playList[index].id) }
                )
            }
        } else {
            ShortsThumbnail(thumbnailUrl = playList[index].thumbnailUrl)
        }
    }
}