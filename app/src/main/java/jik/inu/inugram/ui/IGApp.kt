package jik.inu.inugram.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jik.inu.core.designsystem.component.navigationbar.IGNavigationBar
import jik.inu.core.designsystem.component.navigationbar.IGNavigationBarItem
import jik.inu.core.designsystem.component.navigationbar.IGNavigationButton
import jik.inu.core.designsystem.component.navigationbar.NavigationBarTheme
import jik.inu.core.designsystem.component.toast.IGToast
import jik.inu.core.designsystem.component.toast.LocalToastController
import jik.inu.core.designsystem.component.toast.ToastController
import jik.inu.core.designsystem.component.toast.ToastState
import jik.inu.core.designsystem.theme.INUgramTheme
import jik.inu.feature.upload.navigation.navigateUpload
import jik.inu.inugram.navigation.IGNavHost
import jik.inu.inugram.navigation.TopLevelDestination
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun IGApp(
    viewModel: IGAppViewModel = hiltViewModel()
) {
    val appState = rememberIGAppState()
    val currentTopLevelDestinationOrNull = appState.currentTopLevelDestinationOrNull
    val toastState = remember { ToastState() }
    val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()

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
            snackbarHost = { IGToast(toastState = toastState) },
            contentWindowInsets = WindowInsets(0.dp)
        ) {
            CompositionLocalProvider(
                LocalToastController provides ToastController(toastState)
            ) {
                IGNavHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    navController = appState.navController,
                    startDestination = startDestination,
                    changeNavigationBarTheme = appState::changeNavigationBarTheme
                )
            }
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

    val navTheme by
    rememberUpdatedState(
        newValue = if (theme == NavigationBarTheme.Dark || isSystemInDarkTheme())
            NavigationBarTheme.Dark
        else
            NavigationBarTheme.Light
    )


    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it }
    ) {
        IGNavigationBar(
            modifier = modifier,
            theme = navTheme,
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
                        theme = navTheme,
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
