package jik.inu.feature.certification.greeting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun GreetingScreen(
    modifier: Modifier = Modifier,
    navigateToEmail: () -> Unit
) {

    var showContent by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        showContent = true
        delay(2000L)
        navigateToEmail()
    }

    AnimatedVisibility(
        visible = showContent,
        enter = slideInVertically(animationSpec = tween(durationMillis = 1000)) { it },
    ) {
        Box(
            modifier = modifier.background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "INUgram 을 사용하려면\n인천대 학생을 인증해야 해요",
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}