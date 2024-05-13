package jik.inu.lib.videoplayer.shorts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import jik.inu.lib.videoplayer.shorts.component.LikeButton

@Composable
fun ShortsOverlay(
    modifier: Modifier = Modifier,
    liked: Boolean,
    onLikeClicked: () -> Unit,
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
                LikeButton(
                    liked = liked,
                    onClick = onLikeClicked
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
                    imageVector = Icons.AutoMirrored.Filled.Reply,
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