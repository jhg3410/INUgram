package jik.inu.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import jik.inu.lib.videoplayer.simple.SimpleVideoPlayer

@Composable
fun UploadScreen(
    modifier: Modifier = Modifier,
    viewModel: UploadViewModel = hiltViewModel()
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        SimpleVideoPlayer(contentUri = viewModel.contentUri.toUri())
        Text(
            text = "Upload Screen",
            style = MaterialTheme.typography.titleLarge
        )
    }
}