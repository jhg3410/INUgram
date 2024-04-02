package jik.inu.feature.certification.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
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
            route = GreetingNavigation.route,
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 1000)) }
        ) {
            GreetingScreen(
                navigateToEmail = navigateToEmail
            )
        }
    }

    fun NavGraphBuilder.installCertificationNavGraph(
        navigateToEmail: () -> Unit,
        navigateToCertification: (certificationNumber: String) -> Unit,
        navigateToHome: () -> Unit,
        navigateUp: () -> Unit
    ) {
        installGreetingScreen(navigateToEmail = navigateToEmail)
        installEmailScreen(navigateToCertification = { certificationNumber ->
            navigateToCertification(certificationNumber)
        })
        installCertificationScreen(
            navigateToHome = navigateToHome,
            navigateUp = navigateUp
        )
    }
}