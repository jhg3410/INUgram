package jik.inu.inugram.feature.sign

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.EmailScreen(
    modifier: Modifier = Modifier,
    visible: Boolean
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(durationMillis = 1000)),
        exit = slideOutHorizontally()
    ) {
        Column(
            modifier = modifier
        ) {
            Text(
                text = "인증 번호를 받으실\nINU 이메일을 입력해주세요",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(42.dp))
        }
    }
}