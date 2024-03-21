package jik.inu.inugram.feature.sign

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jik.inu.inugram.designsystem.component.button.IGButton

@Composable
fun EmailScreen(
    modifier: Modifier = Modifier,
    visible: Boolean,
    navigateToCertification: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(durationMillis = 1000)),
        exit = fadeOut(tween(durationMillis = 400))
    ) {
        Column(
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(44.dp))
            Text(
                modifier = Modifier.padding(horizontal = 18.dp),
                text = "인증 번호를 받으실\nINU 이메일을 입력해주세요",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(44.dp))

            // TextField(value = , onValueChange = )

            Spacer(modifier = Modifier.weight(1f))
            IGButton(
                modifier = Modifier.fillMaxWidth(),
                text = "보내기",
                onClick = navigateToCertification
            )
        }
    }
}