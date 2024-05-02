package jik.inu.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jik.inu.feature.home.HomeScreen
import jik.inu.feature.home.navigation.UploadNavigation.installUploadScreen

fun NavController.navigateHome(navOptions: NavOptions? = null) {
    navigate(HomeNavigation.route, navOptions)
}

object HomeNavigation {
    const val route = "Home"

    private fun NavGraphBuilder.installHomeScreen() {
        composable(route = HomeNavigation.route) {
            HomeScreen()
        }
    }

    fun NavGraphBuilder.installHomeNavGraph(navigateToHome: () -> Unit) {
        installHomeScreen()
        installUploadScreen(navigateToHome = navigateToHome)
    }
}