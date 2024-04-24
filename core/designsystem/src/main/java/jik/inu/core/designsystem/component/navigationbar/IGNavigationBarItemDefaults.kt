package jik.inu.core.designsystem.component.navigationbar

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

internal object IGNavigationBarItemDefaults {
    @Composable
    fun selectedIconColor() = MaterialTheme.colorScheme.onSurface

    @Composable
    fun selectedTextColor() = MaterialTheme.colorScheme.onSurface

    @Composable
    fun unselectedIconColor() = MaterialTheme.colorScheme.outline

    @Composable
    fun unselectedTextColor() = MaterialTheme.colorScheme.outline
}