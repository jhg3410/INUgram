package jik.inu.feature.mypage.tab

import androidx.compose.ui.graphics.Color
import jik.inu.core.designsystem.theme.Blue50

internal enum class TabType(
    val contentColor: Color,
    val indicatorColor: Color
) {
    Selected(
        contentColor = Color.Black,
        indicatorColor = Blue50
    ),
    UnSelected(
        contentColor = Color(0xFF909090),
        indicatorColor = Color(0xFFD8D8D8)
    )
}