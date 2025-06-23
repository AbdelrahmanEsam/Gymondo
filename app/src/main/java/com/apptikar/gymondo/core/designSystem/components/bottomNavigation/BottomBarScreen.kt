package com.apptikar.gymondo.core.designSystem.components.bottomNavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.apptikar.gymondo.R
import com.apptikar.gymondo.navigation.Destinations

sealed class BottomBarScreen(
    val route: Destinations,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
) {

    data object Home : BottomBarScreen(
        route = Destinations.HomeScreen,
        title = R.string.main,
        icon = R.drawable.home
    )

    data object Settings : BottomBarScreen(
        route = Destinations.SettingsScreen,
        title = R.string.settings,
        icon = R.drawable.settings
    )

}
