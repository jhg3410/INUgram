//package jik.inu.inugram.navigation.sign
//
//import androidx.navigation.NavController
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavOptions
//import androidx.navigation.compose.composable
//import jik.inu.inugram.feature.sign.SignScreen
//
//object SignNavigation {
//    const val route = "sign"
//
//
//    fun NavController.navigateSign(navOptions: NavOptions? = null) {
//        navigate(route, navOptions)
//    }
//
//    private fun NavGraphBuilder.installSignScreen(
//        navigateToEmailScreen: () -> Unit
//    ) {
//        composable(
//            route = SignNavigation.route
//        ) {
//            SignScreen(
//                navigateToEmailScreen = navigateToEmailScreen
//            )
//        }
//    }
//
//    fun NavGraphBuilder.installSignNavGraph(
//        navigateToEmailScreen: () -> Unit
//    ) {
//        installSignScreen(navigateToEmailScreen = navigateToEmailScreen)
//    }
//}