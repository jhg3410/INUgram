package jik.inu.inugram.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import jik.inu.core.designsystem.icon.IGIcons
import jik.inu.feature.home.navigation.HomeNavigation
import jik.inu.feature.mypage.navigation.MyPageNavigation

enum class TopLevelDestination(
    val route: String,
    val icon: ImageVector,
    val iconText: String,
) {
    HOME(
        route = HomeNavigation.route,
        icon = IGIcons.HomeRounded,
        iconText = "Home",
    ),
    MY_PAGE(
        route = MyPageNavigation.route,
        icon = IGIcons.PersonOutlineRounded,
        iconText = "My",
    )
}