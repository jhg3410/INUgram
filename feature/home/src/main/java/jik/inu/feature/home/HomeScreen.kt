package jik.inu.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jik.inu.core.designsystem.component.navigationbar.NavigationBarTheme
import jik.inu.lib.videoplayer.shorts.Shorts

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    changeNavigationBarTheme: (NavigationBarTheme) -> Unit
) {
    val videos by homeViewModel.videos.collectAsStateWithLifecycle()
    val likedVideos by homeViewModel.likedVideos.collectAsStateWithLifecycle()

    DisposableEffect(key1 = Unit) {
        changeNavigationBarTheme(NavigationBarTheme.Dark)

        onDispose {
            changeNavigationBarTheme(NavigationBarTheme.Light)
        }
    }

    Shorts(
        modifier = modifier,
        playList = videos.map { it.toShortsVideo() },
        likedVideoIds = likedVideos,
        onLikeClicked = { videoId ->
            homeViewModel.handleLike(id = videoId)
        }
    )
}