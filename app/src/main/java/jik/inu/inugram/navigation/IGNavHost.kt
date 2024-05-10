package jik.inu.inugram.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import jik.inu.core.designsystem.component.navigationbar.NavigationBarTheme
import jik.inu.feature.certification.navigation.GreetingNavigation.installCertificationNavGraph
import jik.inu.feature.certification.navigation.navigateCertification
import jik.inu.feature.certification.navigation.navigateEmail
import jik.inu.feature.home.navigation.HomeNavigation
import jik.inu.feature.home.navigation.HomeNavigation.installHomeScreen
import jik.inu.feature.home.navigation.navigateHome
import jik.inu.feature.mypage.navigation.MyPageNavigation.installMyPageNavGraph
import jik.inu.feature.upload.navigation.UploadNavigation.installUploadScreen


@Composable
fun IGNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = HomeNavigation.route,
    changeNavigationBarTheme: (NavigationBarTheme) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        installCertificationNavGraph(
            navigateToEmail = navController::navigateEmail,
            navigateToCertification = { navController.navigateCertification(it) },
            navigateToHome = navController::navigateHome,
            navigateUp = navController::navigateUp
        )

        installHomeScreen(
            changeNavigationBarTheme = changeNavigationBarTheme,
        )

        installUploadScreen(
            navigateToHome = navController::navigateHome
        )

        installMyPageNavGraph(
            changeNavigationBarTheme = changeNavigationBarTheme
        )
    }
}