package jik.inu.inugram.feature.sign

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
fun SignScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        var visiblePage by remember { mutableStateOf(SignPage.NONE) }
        LaunchedEffect(key1 = Unit) {
            visiblePage = SignPage.SIGN
        }

        when (visiblePage) {
            SignPage.SIGN -> {
                LaunchedEffect(key1 = Unit) {
                    delay(2000L)
                    visiblePage = SignPage.EMAIL
                }
            }

            else -> Unit
        }
        SignContent(visible = visiblePage == SignPage.SIGN)
        EmailScreen(
            visible = visiblePage == SignPage.EMAIL,
            navigateToCertification = { visiblePage = SignPage.CERTIFICATION }
        )
        if (visiblePage == SignPage.CERTIFICATION) {
            CertificationScreen(
                visible = visiblePage == SignPage.CERTIFICATION,
                navigateToEmail = { visiblePage = SignPage.EMAIL }
            )
        }
    }
}


@Composable
fun SignContent(
    modifier: Modifier = Modifier,
    visible: Boolean = true
) {
    AnimatedVisibility(
        modifier = modifier.fillMaxSize(),
        visible = visible,
        enter = slideInVertically(animationSpec = tween(durationMillis = 1000)) { it },
        exit = fadeOut(animationSpec = tween(durationMillis = 1000))
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "INUgram 을 사용하려면\n인천대 학생을 인증해야 해요",
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}