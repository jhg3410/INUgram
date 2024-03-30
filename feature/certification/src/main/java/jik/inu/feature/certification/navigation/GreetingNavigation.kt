package jik.inu.feature.certification.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jik.inu.feature.certification.greeting.GreetingScreen
import jik.inu.feature.certification.navigation.CertificationNavigation.installCertificationScreen
import jik.inu.feature.certification.navigation.EmailNavigation.installEmailScreen

object GreetingNavigation {
    const val route = "Greeting"

    private fun NavGraphBuilder.installGreetingScreen(
        navigateToEmail: () -> Unit
    ) {
        composable(
            route = GreetingNavigation.route
        ) {
            GreetingScreen(
                navigateToEmail = navigateToEmail
            )
        }
    }

    fun NavGraphBuilder.installCertificationNavGraph(
        navigateToEmail: () -> Unit,
        navigateToCertification: () -> Unit,
        navigateUp: () -> Unit
    ) {
        installGreetingScreen(navigateToEmail = navigateToEmail)
        installEmailScreen(navigateToCertification = navigateToCertification)
        installCertificationScreen(navigateUp = navigateUp)
    }
}