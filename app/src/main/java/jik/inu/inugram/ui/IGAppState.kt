package jik.inu.inugram.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import jik.inu.core.designsystem.component.navigationbar.NavigationBarTheme
import jik.inu.feature.home.navigation.HomeNavigation
import jik.inu.feature.home.navigation.navigateHome
import jik.inu.feature.mypage.navigation.MyPageNavigation
import jik.inu.feature.mypage.navigation.navigateMyPage
import jik.inu.inugram.navigation.TopLevelDestination

@Composable
fun rememberIGAppState(
    navController: NavHostController = rememberNavController(),
): IGAppState = remember(navController) {
    IGAppState(navController = navController)
}


class IGAppState(
    val navController: NavHostController
) {

    var navigationBarTheme by mutableStateOf(NavigationBarTheme.Light)
        private set

    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestinationOrNull: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HomeNavigation.route -> TopLevelDestination.HOME
            MyPageNavigation.route -> TopLevelDestination.MY_PAGE
            else -> null
        }

    fun navigateFromBottomBar(topLevelDestination: TopLevelDestination) {
        val navOptions = navOptions {
            popUpTo(id = navController.graph.startDestinationId)
            launchSingleTop = true
        }
        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateHome(navOptions)
            TopLevelDestination.MY_PAGE -> navController.navigateMyPage(navOptions)
        }
    }

    fun changeNavigationBarTheme(theme: NavigationBarTheme) {
        navigationBarTheme = theme
    }
}