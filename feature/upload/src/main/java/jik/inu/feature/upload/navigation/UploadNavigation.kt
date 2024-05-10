package jik.inu.feature.upload.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jik.inu.feature.upload.navigation.UploadNavigation.route

fun NavController.navigateUpload(contentUri: String, navOptions: NavOptions? = null) {
    navigate("${route}/$contentUri", navOptions)
}

object UploadNavigation {
    const val contentUriArg = "contentUri"
    const val route = "upload"
    private const val routeWithArgs = "${route}/{$contentUriArg}"

    private val arguments = listOf(
        navArgument(contentUriArg) {
            type = NavType.StringType
        }
    )

    fun NavGraphBuilder.installUploadScreen(
        navigateToHome: () -> Unit
    ) {
        composable(
            route = routeWithArgs,
            arguments = arguments,
            enterTransition = { slideInVertically { it } },
            exitTransition = { slideOutVertically { it } }
        ) {
            jik.inu.feature.upload.UploadScreen(navigateToHome = navigateToHome)
        }
    }
}

internal class UploadArgs(val contentUri: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        contentUri = checkNotNull(savedStateHandle[UploadNavigation.contentUriArg])
    )
}