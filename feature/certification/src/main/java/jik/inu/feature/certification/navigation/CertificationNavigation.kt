package jik.inu.feature.certification.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jik.inu.feature.certification.certification.CertificationScreen


fun NavController.navigateCertification(navOptions: NavOptions? = null) {
    navigate(CertificationNavigation.routeWithArgs, navOptions)
}

object CertificationNavigation {
    const val route = "Certification"
    const val certificationNumberArg = "certificationNumber"
    const val routeWithArgs = "$route/{$certificationNumberArg}"

    private val arguments = listOf(
        navArgument(certificationNumberArg) {
            type = NavType.StringType
        }
    )

    fun NavGraphBuilder.installCertificationScreen(
        navigateToHome: () -> Unit,
        navigateUp: () -> Unit
    ) {
        composable(
            route = routeWithArgs,
            arguments = arguments,
            enterTransition = { slideInHorizontally { it } },
            exitTransition = { slideOutHorizontally { it } }
        ) {
            CertificationScreen(
                navigateToHome = navigateToHome,
                navigateUp = navigateUp
            )
        }
    }
}

internal class CertificationArgs(val certificationNumber: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        certificationNumber = checkNotNull(savedStateHandle[CertificationNavigation.certificationNumberArg])
    )
}