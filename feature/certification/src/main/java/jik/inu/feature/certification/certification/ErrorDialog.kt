package jik.inu.feature.certification.certification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import jik.inu.core.designsystem.component.button.IGButton

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    message: String,
    onConfirm: () -> Unit
) {
    val horizontalPadding = 22.dp
    val buttonWidth = 276.dp

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        ),
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 18.dp)
                .widthIn(max = horizontalPadding * 2 + buttonWidth)
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color.White)
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            IGButton(
                modifier = Modifier.width(buttonWidth),
                onClick = onConfirm,
                text = "확인",
            )
        }
    }
}