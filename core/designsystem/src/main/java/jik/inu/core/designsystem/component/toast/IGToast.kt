package jik.inu.core.designsystem.component.toast

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jik.inu.core.designsystem.theme.SpoqaHanSansNeo
import jik.inu.core.ui.conditional
import kotlinx.coroutines.delay


val LocalToastController = staticCompositionLocalOf {
    ToastController(ToastState())
}

class ToastController(
    private val toastState: ToastState
) {
    fun show(
        message: String,
        type: ToastType,
        bottomPadding: Dp = Dp.Hairline
    ) {
        toastState.show(
            message = message,
            type = type,
            bottomPadding = bottomPadding
        )
    }
}

class ToastState {

    val duration = 2000L
    var message: String by mutableStateOf("")
    var type: ToastType by mutableStateOf(ToastType.SUCCESS)
    var visible: Boolean by mutableStateOf(false)
    var bottomPadding by mutableStateOf(Dp.Hairline)

    internal fun show(
        message: String,
        type: ToastType,
        bottomPadding: Dp
    ) {
        this.message = message
        this.type = type
        this.visible = true
        this.bottomPadding = bottomPadding
    }
}


@Composable
fun IGToast(
    modifier: Modifier = Modifier,
    toastState: ToastState,
    message: String = toastState.message,
    type: ToastType = toastState.type,
    duration: Long = toastState.duration,
    visible: Boolean = toastState.visible,
    bottomPadding: Dp = toastState.bottomPadding
) {
    val isDarkTheme = isSystemInDarkTheme()

    LaunchedEffect(key1 = visible) {
        if (visible) {
            delay(duration)
            toastState.visible = false
        }
    }

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
        val shake = remember { Animatable(0f) }
        val delayForShakeWhenVisible = 500L
        LaunchedEffect(key1 = visible) {
            if (visible) {
                delay(delayForShakeWhenVisible)
                for (i in 0..10) {
                    when (i % 2) {
                        0 -> shake.animateTo(3f, spring(stiffness = 50_000f))
                        else -> shake.animateTo(-3f, spring(stiffness = 50_000f))
                    }
                }
                shake.animateTo(0f)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 48.dp,
                    end = 48.dp,
                    bottom = 20.dp + bottomPadding
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = modifier
                    .offset(x = if (type == ToastType.ERROR) shake.value.dp else 0.dp)
                    .background(
                        color = if (isDarkTheme.not()) type.backgroundColor else Color(0xFF445267),
                        shape = RoundedCornerShape(100.dp)
                    )
                    .conditional(condition = isDarkTheme.not()) {
                        border(
                            width = 1.5.dp,
                            color = type.strokeColor,
                            shape = RoundedCornerShape(100.dp)
                        )
                    }
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
                    color = if (isDarkTheme.not()) Color(0xFF414141) else Color(0xFFF2F2F2)
                )
            }
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewIGToast() {
    val toastState = remember { ToastState() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(onClick = { toastState.show("hihihi", ToastType.WARNING, Dp.Hairline) }) {
            Text("Show Toast")
        }

        IGToast(
            modifier = Modifier,
            toastState = toastState,
        )
    }
}