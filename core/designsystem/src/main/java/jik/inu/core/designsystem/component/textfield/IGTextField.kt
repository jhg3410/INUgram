package jik.inu.core.designsystem.component.textfield

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jik.inu.core.designsystem.component.textfield.visualtransformation.EmailVisualTransformation
import jik.inu.core.ui.toPx
import jik.inu.inugram.designsystem.component.textfield.IGTextFieldDefaults
import jik.inu.inugram.designsystem.component.textfield.IGTextFieldDefaults.dividerActiveColor
import jik.inu.inugram.designsystem.component.textfield.IGTextFieldDefaults.dividerDefaultColor
import jik.inu.inugram.designsystem.component.textfield.IGTextFieldDefaults.textActiveColor
import jik.inu.inugram.designsystem.component.textfield.IGTextFieldDefaults.textDefaultColor
import jik.inu.inugram.designsystem.component.textfield.IGTextFieldType

@Composable
fun IGTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    var type: IGTextFieldType by remember { mutableStateOf(IGTextFieldType.DEFAULT) }

    BasicTextField(
        modifier = modifier.onFocusChanged {
            type = if (it.isFocused) {
                IGTextFieldType.ACTIVE
            } else {
                if (value.isEmpty()) IGTextFieldType.DEFAULT
                else IGTextFieldType.FILLED
            }
        },
        value = value,
        onValueChange = onValueChange,
        visualTransformation = EmailVisualTransformation(),
        singleLine = true,
        decorationBox = { innerTextField ->
            IGTextFieldContainer(
                innerTextField = innerTextField,
                type = type
            )
        }
    )
}


@Composable
private fun IGTextFieldContainer(
    modifier: Modifier = Modifier,
    innerTextField: @Composable () -> Unit,
    type: IGTextFieldType
) {
    var textSize by remember { mutableStateOf(IntSize.Zero) }

    val isActive = type == IGTextFieldType.ACTIVE
    val isDefault = type == IGTextFieldType.DEFAULT

    val scale = IGTextFieldDefaults.textAnimatedScale(if (isDefault) 1f else 0.6f)
    val offset = IGTextFieldDefaults.textAnimatedPosition(
        if (isDefault) IntOffset.Zero
        else IntOffset(-((textSize.width * 0.4f) / 2).toInt(), -20.sp.toPx.toInt() - 16)
    )

    Column(modifier = modifier) {
        Box {
            innerTextField()
            Text(
                modifier = Modifier
                    .onGloballyPositioned {
                        if (textSize == IntSize.Zero) {
                            textSize = it.size
                        }
                    }
                    .offset {
                        offset
                    }
                    .align(alignment = Alignment.TopStart)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    },
                text = "이메일 입력",
                fontSize = 20.sp,
                color = IGTextFieldDefaults.animatedColor(
                    targetColor = if (isActive) textActiveColor else textDefaultColor
                )
            )
        }
        Spacer(modifier = Modifier.height(if (isActive) 6.dp else 6.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = IGTextFieldDefaults.animatedColor(
                targetColor = if (isActive) dividerActiveColor else dividerDefaultColor
            )
        )
    }
}