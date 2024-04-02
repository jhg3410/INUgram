package jik.inu.feature.certification.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jik.inu.feature.certification.email.EmailScreen

fun NavController.navigateEmail(navOptions: NavOptions? = null) {
    navigate(EmailNavigation.route, navOptions)
}

object EmailNavigation {
    const val route = "email"

    fun NavGraphBuilder.installEmailScreen(
        navigateToCertification: (certificationNumber: String) -> Unit
    ) {
        composable(
            route = EmailNavigation.route,
            enterTransition = { fadeIn(tween(durationMillis = 1000)) },
            exitTransition = { fadeOut(tween(durationMillis = 400)) }
        ) {
            EmailScreen(navigateToCertification = navigateToCertification)
        }
    }
}