package jik.inu.inugram.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import jik.inu.feature.certification.navigation.GreetingNavigation.installCertificationNavGraph
import jik.inu.feature.certification.navigation.navigateCertification
import jik.inu.feature.certification.navigation.navigateEmail
import jik.inu.feature.home.navigation.HomeNavigation
import jik.inu.feature.home.navigation.HomeNavigation.installHomeNavGraph
import jik.inu.feature.home.navigation.UploadNavigation.installUploadScreen
import jik.inu.feature.home.navigation.navigateHome
import jik.inu.feature.mypage.navigation.MyPageNavigation.installMyPageNavGraph


@Composable
fun IGNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = HomeNavigation.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        installCertificationNavGraph(
            navigateToEmail = navController::navigateEmail,
            navigateToCertification = { navController.navigateCertification(it) },
            navigateToHome = navController::navigateHome,
            navigateUp = navController::navigateUp
        )

        installHomeNavGraph()

        installUploadScreen()

        installMyPageNavGraph()
    }
}