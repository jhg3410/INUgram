package jik.inu.feature.certification.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jik.inu.feature.certification.certification.CertificationScreen


fun NavController.navigateCertification(navOptions: NavOptions? = null) {
    navigate(CertificationNavigation.route, navOptions)
}

object CertificationNavigation {
    const val route = "Certification"

    fun NavGraphBuilder.installCertificationScreen(
        navigateUp: () -> Unit
    ) {
        composable(
            route = GreetingNavigation.route,
            enterTransition = { slideInHorizontally { it } },
            exitTransition = { slideOutHorizontally { it } }
        ) {
            CertificationScreen(
                navigateUp = navigateUp
            )
        }
    }
}