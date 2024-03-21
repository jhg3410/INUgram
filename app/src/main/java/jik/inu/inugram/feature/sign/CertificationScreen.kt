package jik.inu.inugram.feature.sign

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
fun CertificationScreen(
    modifier: Modifier = Modifier,
    visible: Boolean,
    navigateToEmail: () -> Unit
) {
    BackHandler {
        navigateToEmail()
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally { it },
        exit = slideOutHorizontally { it }
    ) {
        Column(
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(44.dp))
            Text(
                modifier = Modifier.padding(horizontal = 18.dp),
                text = "인증 번호를 보냈어요\nINU 이메일을 확인해주세요",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(44.dp))



            Spacer(modifier = Modifier.weight(1f))
            IGButton(
                modifier = Modifier.fillMaxWidth(),
                text = "인증하기",
                onClick = {}
            )
        }
    }
}