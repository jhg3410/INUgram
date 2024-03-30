package jik.inu.inugram.designsystem.component.button

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
import jik.inu.inugram.designsystem.theme.Blue50
import jik.inu.core.theme.SpoqaHanSansNeo

@Composable
fun IGButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(color = Blue50),
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