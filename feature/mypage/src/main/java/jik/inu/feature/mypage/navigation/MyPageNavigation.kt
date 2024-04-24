package jik.inu.feature.mypage.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jik.inu.feature.mypage.MyPageScreen

fun NavController.navigateMyPage(navOptions: NavOptions? = null) {
    navigate(MyPageNavigation.route, navOptions)
}

object MyPageNavigation {
    const val route = "MyPage"

    private fun NavGraphBuilder.installMyPageScreen() {
        composable(
            route = MyPageNavigation.route,
            enterTransition = { slideInHorizontally { it } },
            exitTransition = { slideOutHorizontally { it } }
        ) {
            MyPageScreen()
        }
    }

    fun NavGraphBuilder.installMyPageNavGraph() {
        installMyPageScreen()
    }
}