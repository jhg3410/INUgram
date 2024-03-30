package jik.inu.inugram.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import jik.inu.feature.certification.navigation.GreetingNavigation
import jik.inu.feature.certification.navigation.GreetingNavigation.installCertificationNavGraph
import jik.inu.feature.certification.navigation.navigateCertification
import jik.inu.feature.certification.navigation.navigateEmail
import jik.inu.feature.home.navigation.HomeNavigation.installHomeNavGraph
import jik.inu.feature.home.navigation.navigateHome


@Composable
fun IGNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = GreetingNavigation.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        installCertificationNavGraph(
            navigateToEmail = navController::navigateEmail,
            navigateToCertification = navController::navigateCertification,
            navigateToHome = navController::navigateHome,
            navigateUp = navController::navigateUp
        )

        installHomeNavGraph()
    }
}