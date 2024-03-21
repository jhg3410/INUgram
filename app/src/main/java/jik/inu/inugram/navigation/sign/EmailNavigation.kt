//package jik.inu.inugram.navigation.sign
//
//import androidx.navigation.NavController
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavOptions
//import androidx.navigation.compose.composable
//import jik.inu.inugram.feature.sign.SignScreen
//
//object EmailNavigation {
//    const val route = "sign"
//
//
//    fun NavController.navigateEmail(navOptions: NavOptions? = null) {
//        navigate(route, navOptions)
//    }
//
//    private fun NavGraphBuilder.installEmailScreen(
//        navigateToEmailScreen: () -> Unit
//    ) {
//        composable(
//            route = EmailNavigation.route
//        ) {
//            EmailScreen(
//                navigateToEmailScreen = navigateToEmailScreen
//            )
//        }
//    }
//}