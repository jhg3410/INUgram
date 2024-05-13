package jik.inu.core.designsystem.component.navigationbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

internal object IGNavigationBarItemDefaults {
    @Composable
    fun selectedIconColor(
        theme: NavigationBarTheme
    ) = when (theme) {
        NavigationBarTheme.Light -> Color.Black
        NavigationBarTheme.Dark -> Color.White
    }

    @Composable
    fun selectedTextColor(
        theme: NavigationBarTheme
    ) = when (theme) {
        NavigationBarTheme.Light -> Color.Black
        NavigationBarTheme.Dark -> Color.White
    }

    @Composable
    fun unselectedIconColor(
        theme: NavigationBarTheme
    ) = when (theme) {
        NavigationBarTheme.Light -> Color.Black.copy(alpha = 0.5f)
        NavigationBarTheme.Dark -> Color.White.copy(alpha = 0.5f)
    }

    @Composable
    fun unselectedTextColor(
        theme: NavigationBarTheme
    ) = when (theme) {
        NavigationBarTheme.Light -> Color.Black.copy(alpha = 0.5f)
        NavigationBarTheme.Dark -> Color.White.copy(alpha = 0.5f)
    }
}

enum class NavigationBarTheme(
    val backgroundColor: Color,
    val borderColor: Color
) {
    Light(
        backgroundColor = Color.White,
        borderColor = Color.Black.copy(alpha = 0.2f)
    ),
    Dark(
        backgroundColor = Color.Black,
        borderColor = Color(0xFF343434)
    )
}