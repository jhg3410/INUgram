package jik.inu.feature.home.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jik.inu.feature.home.HomeScreen

fun NavController.navigateHome(navOptions: NavOptions? = null) {
    navigate(HomeNavigation.route, navOptions)
}

object HomeNavigation {
    const val route = "Home"

    private fun NavGraphBuilder.installHomeScreen() {
        composable(
            route = HomeNavigation.route,
            enterTransition = { slideInHorizontally { it } },
            exitTransition = { slideOutHorizontally { it } }
        ) {
            HomeScreen()
        }
    }

    fun NavGraphBuilder.installHomeNavGraph() {
        installHomeScreen()
    }
}