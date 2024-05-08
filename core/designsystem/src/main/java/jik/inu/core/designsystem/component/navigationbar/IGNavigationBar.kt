package jik.inu.core.designsystem.component.navigationbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jik.inu.core.designsystem.component.navigationbar.IGNavigationBarItemDefaults.selectedIconColor
import jik.inu.core.designsystem.component.navigationbar.IGNavigationBarItemDefaults.selectedTextColor
import jik.inu.core.designsystem.component.navigationbar.IGNavigationBarItemDefaults.unselectedIconColor
import jik.inu.core.designsystem.component.navigationbar.IGNavigationBarItemDefaults.unselectedTextColor
import jik.inu.core.designsystem.icon.IGIcons


@Composable
fun IGNavigationBar(
    modifier: Modifier = Modifier,
    theme: NavigationBarTheme,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        modifier = modifier,
        color = theme.backgroundColor,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                .selectableGroup(),
            content = content
        )
    }
}

@Composable
fun RowScope.IGNavigationBarItem(
    modifier: Modifier = Modifier,
    theme: NavigationBarTheme,
    selected: Boolean,
    iconImageVector: ImageVector,
    selectedIconImageVector: ImageVector = iconImageVector,
    labelText: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.8f else 1f, label = "")

    Box(
        modifier = modifier
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null,
            )
            .weight(1f)
            .scale(scale)
            .padding(vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        MovieNavigationBarItemLabelAndIcon(
            selected = selected,
            theme = theme,
            iconImageVector = iconImageVector,
            selectedIconImageVector = selectedIconImageVector,
            labelText = labelText
        )
    }
}

@Composable
private fun MovieNavigationBarItemLabelAndIcon(
    selected: Boolean,
    theme: NavigationBarTheme,
    iconImageVector: ImageVector,
    selectedIconImageVector: ImageVector,
    labelText: String,
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = if (selected) selectedIconImageVector else iconImageVector,
            tint = if (selected) selectedIconColor(theme) else unselectedIconColor(theme),
            contentDescription = null
        )
        Text(
            text = labelText,
            color = if (selected) selectedTextColor(theme) else unselectedTextColor(theme),
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun IGNavigationBarPreview() {
    IGNavigationBar(theme = NavigationBarTheme.Light) {
        IGNavigationBarItem(
            selected = true,
            theme = NavigationBarTheme.Light,
            iconImageVector = IGIcons.Error,
            labelText = "hihi",
            onClick = {}
        )
        IGNavigationButton(onClick = {})
        IGNavigationBarItem(
            selected = true,
            theme = NavigationBarTheme.Light,
            iconImageVector = IGIcons.Error,
            labelText = "hihi",
            onClick = {}
        )
    }
}