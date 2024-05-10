package jik.inu.inugram.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jik.inu.core.designsystem.component.navigationbar.IGNavigationBar
import jik.inu.core.designsystem.component.navigationbar.IGNavigationBarItem
import jik.inu.core.designsystem.component.navigationbar.IGNavigationButton
import jik.inu.core.designsystem.component.navigationbar.NavigationBarTheme
import jik.inu.core.designsystem.theme.INUgramTheme
import jik.inu.feature.home.navigation.navigateUpload
import jik.inu.inugram.navigation.IGNavHost
import jik.inu.inugram.navigation.TopLevelDestination
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun IGApp() {
    val appState = rememberIGAppState()
    val currentTopLevelDestinationOrNull = appState.currentTopLevelDestinationOrNull

    INUgramTheme {
        Scaffold(
            bottomBar = {
                IGBottomBar(
                    visible = currentTopLevelDestinationOrNull != null,
                    theme = appState.navigationBarTheme,
                    topLevelDestination = TopLevelDestination.entries,
                    currentTopLevelDestination = currentTopLevelDestinationOrNull,
                    onNavigateToDestination = { destination ->
                        appState.navigateFromBottomBar(destination)
                    },
                    navigateToUpload = { contentUri ->
                        val encodedUrl =
                            URLEncoder.encode(contentUri, StandardCharsets.UTF_8.toString())
                        appState.navController.navigateUpload(contentUri = encodedUrl)
                    }
                )
            },
            contentWindowInsets = WindowInsets(0.dp)
        ) {
            IGNavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                navController = appState.navController,
                changeNavigationBarTheme = appState::changeNavigationBarTheme
            )
        }
    }
}


@Composable
fun IGBottomBar(
    visible: Boolean,
    theme: NavigationBarTheme,
    topLevelDestination: List<TopLevelDestination>,
    currentTopLevelDestination: TopLevelDestination?,
    modifier: Modifier = Modifier,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    navigateToUpload: (contentUri: String) -> Unit
) {
    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        it?.let {
            navigateToUpload(it.toString())
        }
    }


    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it }
    ) {
        IGNavigationBar(
            modifier = modifier,
            theme = theme,
            content = {
                topLevelDestination.forEachIndexed { index, destination ->
                    val selected = destination.route == currentTopLevelDestination?.route
                    if (index == topLevelDestination.size / 2) {
                        IGNavigationButton(
                            onClick = {
                                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
                            }
                        )
                    }
                    IGNavigationBarItem(
                        selected = selected,
                        theme = theme,
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
