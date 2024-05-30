package jik.inu.feature.certification.certification

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jik.inu.core.designsystem.component.button.IGButton
import jik.inu.core.designsystem.component.toast.LocalToastController
import jik.inu.core.designsystem.component.toast.ToastType


@Composable
fun CertificationScreen(
    modifier: Modifier = Modifier,
    viewModel: CertificationViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateUp: () -> Unit
) {

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val inputNumber by viewModel.inputNumber.collectAsStateWithLifecycle()
    val igToastController = LocalToastController.current

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = modifier
            .statusBarsPadding()
            .imePadding()
            .background(color = MaterialTheme.colorScheme.background)
            .pointerInput(key1 = Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
    ) {
        Spacer(modifier = Modifier.height(44.dp))
        Text(
            modifier = Modifier.padding(horizontal = 18.dp),
            text = "인증 번호를 보냈어요\nINU 이메일을 확인해주세요",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(44.dp))
        CertificationNumberField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .focusRequester(focusRequester),
            value = inputNumber,
            onValueChange = { input ->
                viewModel.changeInputNumber(input = input)
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        IGButton(
            modifier = Modifier.fillMaxWidth(),
            text = "인증하기",
            onClick = {
                viewModel.certify(
                    action = {
                        focusManager.clearFocus()
                        navigateToHome()
                    },
                    onFailure = { message ->
                        igToastController.show(
                            message = message,
                            type = ToastType.WARNING,
                            bottomPadding = 62.dp
                        )
                    }
                )
            },
            enable = inputNumber.length == 5
        )
    }
}

@Composable
private fun CertificationNumberField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                value.forEachIndexed { index, char ->
                    CertificationNumberFieldContainer(
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f),
                        text = char,
                        isFocused = index == value.lastIndex
                    )
                }
                repeat(5 - value.length) {
                    CertificationNumberFieldContainer(
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f),
                        text = ' ',
                        isFocused = false
                    )
                }
            }
        }
    )
}


@Composable
private fun CertificationNumberFieldContainer(
    modifier: Modifier = Modifier,
    text: Char,
    isFocused: Boolean
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color = Color(0xFFF3F4F4))
            .run {
                if (isFocused) {
                    border(
                        width = 1.dp,
                        color = if (isSystemInDarkTheme().not()) Color.Black else Color.Transparent,
                        shape = RoundedCornerShape(4.dp)
                    )
                } else {
                    this
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.toString(),
            color = Color.Black,
            fontSize = 40.sp
        )
    }
}