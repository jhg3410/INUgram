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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


enum class ToastType(
    val icon: ImageVector,
    val iconColor: Color,
    val strokeColor: Color,
    val contentDescription: String = this.toString()
) {
    SUCCESS(
        icon = Icons.Rounded.CheckCircle,
        iconColor = Color(0xFF00B800),
        strokeColor = Color(0xFF78D361),
    ),
    ERROR(
        icon = Icons.Rounded.AddCircle,
        iconColor = Color(0xFFFF0000),
        strokeColor = Color(0xFFFF8181),
    ),
    WARNING(
        icon = Icons.Rounded.Error,
        iconColor = Color(0xFFFFA800),
        strokeColor = Color(0xFFFFC581),
    )
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
                        color = type.strokeColor,
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
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(if (type == ToastType.ERROR) 45f else 0f),
                    imageVector = type.icon,
                    contentDescription = type.contentDescription,
                    tint = type.iconColor
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


@Preview
@Composable
fun PreviewIGToast() {
    IGToast(
        modifier = Modifier,
        type = ToastType.SUCCESS,
        message = "토스트 메시지",
        visible = true
    )
}