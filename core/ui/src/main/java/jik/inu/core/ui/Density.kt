package jik.inu.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit

val TextUnit.toPx: Float
    @Composable get() = with(LocalDensity.current) { this@toPx.toPx() }
