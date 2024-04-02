package jik.inu.feature.certification.email

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jik.inu.core.designsystem.component.LoadingWheel
import jik.inu.core.designsystem.component.button.IGButton
import jik.inu.core.designsystem.component.textfield.IGTextField

@Composable
fun EmailScreen(
    modifier: Modifier = Modifier,
    viewModel: EmailViewModel = hiltViewModel(),
    navigateToCertification: (certificationNumber: String) -> Unit
) {

    val email by viewModel.inputEmail.collectAsStateWithLifecycle()
    val certificationNumber by viewModel.certificationNumber.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    val focusManager = LocalFocusManager.current


    LaunchedEffect(key1 = certificationNumber) {
        if (certificationNumber.isNotEmpty()) {
            navigateToCertification(certificationNumber)
            viewModel.changeCertificationNumber(number = "")
        }
    }

    if (isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoadingWheel(
                modifier = modifier,
            )
        }

    }

    Column(
        modifier = modifier
            .pointerInput(key1 = Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
    ) {
        Spacer(modifier = Modifier.height(44.dp))
        Text(
            modifier = Modifier.padding(horizontal = 18.dp),
            text = "인증 번호를 받으실\nINU 이메일을 입력해주세요",
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(44.dp))
        IGTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            value = email,
            onValueChange = { input -> viewModel.changeEmail(input) }
        )
        Spacer(modifier = Modifier.weight(1f))
        IGButton(
            modifier = Modifier.fillMaxWidth(),
            enable = email.isNotEmpty() && isLoading.not(),
            text = "보내기",
            onClick = {
                viewModel.sendEmail(email)
            }
        )
    }
}