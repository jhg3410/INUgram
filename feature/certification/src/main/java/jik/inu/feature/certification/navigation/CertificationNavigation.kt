package jik.inu.feature.certification.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jik.inu.feature.certification.certification.CertificationScreen


fun NavController.navigateCertification(
    certificationNumber: String,
    navOptions: NavOptions? = null
) {
    navigate("${CertificationNavigation.route}/$certificationNumber", navOptions)
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
            exitTransition = { fadeOut(tween(durationMillis = 400)) }
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