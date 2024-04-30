package jik.inu.feature.home.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jik.inu.feature.home.UploadScreen
import jik.inu.feature.home.navigation.UploadNavigation.route

fun NavController.navigateUpload(contentUri: String, navOptions: NavOptions? = null) {
    navigate("${route}/$contentUri", navOptions)
}

object UploadNavigation {
    const val contentUriArg = "contentUri"
    const val route = "upload"
    private const val routeWithArgs = "${route}/{${contentUriArg}}"

    private val arguments = listOf(
        navArgument(contentUriArg) {
            type = NavType.StringType
        }
    )

    fun NavGraphBuilder.installUploadScreen() {
        composable(
            route = routeWithArgs,
            arguments = arguments,
            enterTransition = { slideInVertically { it } },
            exitTransition = { slideOutVertically { it } }
        ) {
            UploadScreen()
        }
    }
}

internal class UploadArgs(val contentUri: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        contentUri = checkNotNull(savedStateHandle[UploadNavigation.contentUriArg])
    )
}