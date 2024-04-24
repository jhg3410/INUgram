package jik.inu.inugram.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jik.inu.core.designsystem.component.navigationbar.IGNavigationBar
import jik.inu.core.designsystem.component.navigationbar.IGNavigationBarItem
import jik.inu.core.designsystem.component.navigationbar.IGNavigationButton
import jik.inu.core.designsystem.theme.INUgramTheme
import jik.inu.inugram.navigation.IGNavHost
import jik.inu.inugram.navigation.TopLevelDestination

@Composable
fun IGApp() {
    val appState = rememberIGAppState()
    val currentTopLevelDestinationOrNull = appState.currentTopLevelDestinationOrNull

    INUgramTheme {
        Scaffold(
            bottomBar = {
                IGBottomBar(
                    visible = currentTopLevelDestinationOrNull != null,
                    topLevelDestination = TopLevelDestination.entries,
                    currentTopLevelDestination = currentTopLevelDestinationOrNull,
                    onNavigateToDestination = { destination ->
                        appState.navigateFromBottomBar(destination)
                    }
                )
            }
        ) {
            IGNavHost(
                modifier = Modifier.padding(it),
                navController = appState.navController
            )
        }
    }
}


@Composable
fun IGBottomBar(
    visible: Boolean,
    topLevelDestination: List<TopLevelDestination>,
    currentTopLevelDestination: TopLevelDestination?,
    modifier: Modifier = Modifier,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it }
    ) {
        IGNavigationBar(
            modifier = modifier,
            content = {
                topLevelDestination.forEachIndexed { index, destination ->
                    val selected = destination.route == currentTopLevelDestination?.route
                    if (index == topLevelDestination.size / 2) {
                        IGNavigationButton(onClick = {})
                    }
                    IGNavigationBarItem(
                        selected = selected,
                        onClick = { onNavigateToDestination(destination) },
                        iconImageVector = destination.icon,
                        selectedIconImageVector = destination.icon,
                        labelText = destination.iconText,
                    )
                }
            }
        )
    }
}
