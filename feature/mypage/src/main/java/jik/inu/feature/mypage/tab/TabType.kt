package jik.inu.feature.mypage.tab

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import jik.inu.core.designsystem.theme.Blue50
import jik.inu.core.designsystem.theme.BlueBlack
import jik.inu.core.designsystem.theme.Gray10

internal enum class TabType(
    val contentColor: @Composable () -> Color,
    val indicatorColor: Color
) {
    Selected(
        contentColor = @Composable {
            if (isSystemInDarkTheme()) Gray10
            else BlueBlack
        },
        indicatorColor = Blue50
    ),
    UnSelected(
        contentColor = {
            Color(0xFF909090)
        },
        indicatorColor = Color(0xFFD8D8D8)
    )
}