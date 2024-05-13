package jik.inu.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jik.inu.core.designsystem.component.navigationbar.NavigationBarTheme
import jik.inu.feature.mypage.tab.MyPageTabRow

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    myPageViewModel: MyPageViewModel = hiltViewModel(),
    changeNavigationBarTheme: (NavigationBarTheme) -> Unit
) {
    val selectedTabIndex by myPageViewModel.selectedTabIndex.collectAsStateWithLifecycle()
    val email by myPageViewModel.email.collectAsStateWithLifecycle()
    val profileColor by myPageViewModel.profileColor.collectAsStateWithLifecycle()

    val likedVideos by myPageViewModel.likedVideos.collectAsStateWithLifecycle()
    val myVideos by myPageViewModel.myVideos.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            profileColor.copy(0.5f),
                            profileColor.copy(0.2f),
                            Color.White
                        )
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.statusBarsPadding())
            Spacer(modifier = Modifier.height(40.dp))
            MyInfo(profileColor = profileColor, email = email)
            Spacer(modifier = Modifier.height(40.dp))
        }
        MyPageTabRow(
            tabs = myPageViewModel.tabs,
            selectedTabIndex = selectedTabIndex,
            onChangeSelectedTabIndex = {
                myPageViewModel.onSelectedTabChanged(it)
            }
        )
        MyVideosScreen(
            modifier = Modifier.weight(1f),
            videos = if (selectedTabIndex == 0) likedVideos else myVideos
        )
    }
}

@Composable
private fun MyInfo(profileColor: Color, email: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(profileColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = (email.firstOrNull() ?: "").toString(),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = email,
            color = Color.Black,
            style = MaterialTheme.typography.bodySmall
        )
    }
}