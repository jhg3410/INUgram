package jik.inu.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jik.inu.core.theme.SpoqaHanSansNeo
import jik.inu.core.theme.Blue50

@Composable
fun IGButton(
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    text: String,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .clickable(onClick = onClick, enabled = enable)
            .background(color = if (enable) Blue50 else Color(0xFFAAAAAA)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(15.dp),
            text = text,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = SpoqaHanSansNeo
        )
    }
}