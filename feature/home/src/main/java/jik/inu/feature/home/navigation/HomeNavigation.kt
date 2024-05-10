package jik.inu.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jik.inu.core.designsystem.component.navigationbar.NavigationBarTheme
import jik.inu.feature.home.HomeScreen

fun NavController.navigateHome(navOptions: NavOptions? = null) {
    navigate(HomeNavigation.route, navOptions)
}

object HomeNavigation {
    const val route = "Home"

    fun NavGraphBuilder.installHomeScreen(
        changeNavigationBarTheme: (NavigationBarTheme) -> Unit
    ) {
        composable(route = HomeNavigation.route) {
            HomeScreen(changeNavigationBarTheme = changeNavigationBarTheme)
        }
    }
}