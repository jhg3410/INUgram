package jik.inu.core.designsystem.component.toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jik.inu.core.designsystem.icon.IGIcons
import jik.inu.core.designsystem.theme.SpoqaHanSansNeo
import kotlinx.coroutines.delay


object IGToast {
    @Composable
    fun Show(
        modifier: Modifier = Modifier,
        message: String,
        type: ToastType,
        duration: Long = 2000L,
        visible: Boolean,
        onDismiss: () -> Unit
    ) {
        if (visible) {
            LaunchedEffect(key1 = Unit) {
                delay(duration)
                onDismiss()
            }
        }

        IGToast(
            modifier = modifier,
            type = type,
            message = message,
            visible = visible
        )
    }
}


enum class ToastType {
    WARNING,
    // todo: ERROR,
    // todo: SUCCESS
}


@Composable
fun IGToast(
    modifier: Modifier,
    type: ToastType,
    message: String,
    visible: Boolean
) {
    AnimatedVisibility(
        modifier = modifier
            .imePadding()
            .fillMaxSize(),
        visible = visible,
        enter = slideInVertically { it },
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 300),
            targetOffsetY = { it }
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 48.dp,
                    end = 48.dp,
                    bottom = 82.dp
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = modifier
                    .background(
                        color = Color(0xFFFFFCF2),
                        shape = RoundedCornerShape(100.dp)
                    )
                    .border(
                        width = 1.5.dp,
                        color = Color(0xFFFFC581),
                        shape = RoundedCornerShape(100.dp)
                    )
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start = 14.dp,
                        end = 24.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = IGIcons.Error,
                    contentDescription = "warning",
                    tint = Color(0xFFFFA800)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = message,
                    fontSize = 14.sp,
                    fontFamily = SpoqaHanSansNeo,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF414141)
                )
            }
        }
    }
}
