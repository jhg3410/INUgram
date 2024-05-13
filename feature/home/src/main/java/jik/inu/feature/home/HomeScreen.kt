package jik.inu.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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
    val systemUiController = rememberSystemUiController()

    DisposableEffect(key1 = Unit) {
        changeNavigationBarTheme(NavigationBarTheme.Dark)
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )

        onDispose {
            changeNavigationBarTheme(NavigationBarTheme.Light)
            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = true
            )
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