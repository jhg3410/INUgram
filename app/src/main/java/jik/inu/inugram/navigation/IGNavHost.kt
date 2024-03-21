//package jik.inu.inugram.navigation
//
//import androidx.compose.animation.slideInVertically
//import androidx.compose.animation.slideOutVertically
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import jik.inu.inugram.navigation.sign.SignNavigation
//import jik.inu.inugram.navigation.sign.SignNavigation.installSignNavGraph
//
//@Composable
//fun IGNavHost(
//    navController: NavHostController,
//    modifier: Modifier = Modifier,
//    startDestination: String = SignNavigation.route,
//) {
//    NavHost(
//        navController = navController,
//        startDestination = startDestination,
//        modifier = modifier
//    ) {
//        installSignNavGraph(
//            navigateToEmailScreen = { navController.navigateEmail() }
//        )
//        installPopularScreen(
//            onPosterClick = { movieId -> navController.navigateDetail(movieId) }
//        )
//        installDetailScreen(
//            enterTransition = slideInVertically { it },
//            exitTransition = slideOutVertically { it },
//            navigateUp = { navController.navigateUp() }
//        )
//    }
//}