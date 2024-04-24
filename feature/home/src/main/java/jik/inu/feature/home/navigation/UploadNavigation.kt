package jik.inu.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jik.inu.feature.home.UploadScreen

fun NavController.navigateUpload(navOptions: NavOptions? = null) {
    navigate(HomeNavigation.route, navOptions)
}

object UploadNavigation {
    const val route = "Upload"

    fun NavGraphBuilder.installUploadScreen() {
        composable(route = HomeNavigation.route) {
            UploadScreen()
        }
    }
}