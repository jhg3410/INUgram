package jik.inu.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jik.inu.core.designsystem.component.navigationbar.NavigationBarTheme
import jik.inu.feature.home.HomeScreen
import jik.inu.feature.home.navigation.UploadNavigation.installUploadScreen

fun NavController.navigateHome(navOptions: NavOptions? = null) {
    navigate(HomeNavigation.route, navOptions)
}

object HomeNavigation {
    const val route = "Home"

    private fun NavGraphBuilder.installHomeScreen(
        changeNavigationBarTheme: (NavigationBarTheme) -> Unit
    ) {
        composable(route = HomeNavigation.route) {
            HomeScreen(changeNavigationBarTheme = changeNavigationBarTheme)
        }
    }

    fun NavGraphBuilder.installHomeNavGraph(
        changeNavigationBarTheme: (NavigationBarTheme) -> Unit,
        navigateToHome: () -> Unit
    ) {
        installHomeScreen(changeNavigationBarTheme = changeNavigationBarTheme)
        installUploadScreen(navigateToHome = navigateToHome)
    }
}