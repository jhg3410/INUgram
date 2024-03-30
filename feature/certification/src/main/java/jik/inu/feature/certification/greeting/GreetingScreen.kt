package jik.inu.feature.certification.greeting

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun GreetingScreen(
    modifier: Modifier = Modifier,
    navigateToEmail: () -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = "INUgram 을 사용하려면\n인천대 학생을 인증해야 해요",
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge
        )
    }
}