package jik.inu.core.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import jik.inu.core.theme.Blue50

@Composable
fun LoadingWheel(
    modifier: Modifier = Modifier,
    circleSize: Dp = 40.dp,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    contentPadding: PaddingValues = PaddingValues()
) {

    CircularProgressIndicator(
        color = Blue50,
        strokeWidth = strokeWidth,
        modifier = modifier
            .size(circleSize)
            .padding(contentPadding)
    )
}