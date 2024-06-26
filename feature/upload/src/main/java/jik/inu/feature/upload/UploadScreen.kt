package jik.inu.feature.upload

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jik.inu.core.designsystem.R
import jik.inu.core.designsystem.component.LoadingWheel
import jik.inu.core.designsystem.component.toast.LocalToastController
import jik.inu.core.designsystem.component.toast.ToastType
import jik.inu.core.designsystem.theme.BlueBlack
import jik.inu.lib.videoplayer.simple.SimpleVideoPlayer

@Composable
fun UploadScreen(
    modifier: Modifier = Modifier,
    viewModel: UploadViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {
    val textInput by viewModel.inputDescription.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val igToastController = LocalToastController.current

    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .imePadding()
            .verticalScroll(rememberScrollState())
            .pointerInput(key1 = Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {
        SimpleVideoPlayer(
            modifier = Modifier
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(16.dp)
                )
                .aspectRatio(300f / 484f),
            contentUri = viewModel.contentUri
        )
        Spacer(modifier = Modifier.height(22.dp))
        DescriptionField(
            input = textInput,
            onInputChange = { viewModel.changeDescription(it) }
        )
        Spacer(modifier = Modifier.height(22.dp))
        UploadButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = isLoading.not() && textInput.text.isNotBlank(),
            onClick = {
                viewModel.upload(
                    onSuccess = { message ->
                        navigateToHome()
                        igToastController.show(
                            message = message,
                            type = ToastType.SUCCESS
                        )
                    },
                    onFailure = { message ->
                        igToastController.show(
                            message = message,
                            type = ToastType.ERROR,
                            bottomPadding = 60.dp
                        )
                    }
                )
            }
        )
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoadingWheel()
        }
    }
}

@Composable
fun DescriptionField(
    modifier: Modifier = Modifier,
    input: TextFieldValue,
    onInputChange: (TextFieldValue) -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = "input Description",
                tint = if (isDarkTheme.not()) Color(0xFF161719) else Color(0xFFF0F0F0)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "설명 추가하기",
                style = MaterialTheme.typography.labelSmall,
                color = if (isDarkTheme.not()) Color(0xFF161719) else Color(0xFFF0F0F0)
            )
        }
        UploadTextField(
            value = input,
            onValueChange = onInputChange
        )
    }
}

@Composable
private fun UploadTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {

    val isDarkTheme = isSystemInDarkTheme()
    val contentLightColor = Color(0xFF161719)
    val contentDarkColor = Color(0xFFF0F0F0)
    val contentColor = if (isDarkTheme.not()) contentLightColor else contentDarkColor

    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = contentColor
        ),
        singleLine = false,
        minLines = 4,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF39A1DC),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(
                        color = if (isSystemInDarkTheme().not()) Color.White else Color(0xFF222531),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
            ) {
                if (value.text.isEmpty()) {
                    Text(
                        text = "설명을 입력해주세요",
                        style = MaterialTheme.typography.bodyMedium,
                        color = contentColor
                    )
                }
                innerTextField()
            }
        },
        cursorBrush = SolidColor(contentColor)
    )
}


@Composable
private fun UploadButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.95f else 1f, label = "upload")
    val buttonBackgroundColor =
        if (enabled.not()) {
            Color(0xFFB0B0B0)
        } else if (
            isSystemInDarkTheme()
        ) {
            MaterialTheme.colorScheme.primary
        } else {
            BlueBlack
        }


    Box(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
            .scale(scale = scale)
            .background(
                color = buttonBackgroundColor,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = "영상 업로드 하기",
            color = Color.White
        )
    }
}