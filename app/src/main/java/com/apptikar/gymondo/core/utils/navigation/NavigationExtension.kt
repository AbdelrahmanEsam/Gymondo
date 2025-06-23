package com.apptikar.gymondo.core.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.apptikar.gymondo.navigation.Destinations
import com.apptikar.gymondo.navigation.Destinations.HomeScreen

fun NavHostController.navigateSingleTopTo(route: Destinations) =
    this.navigate(route) {
        popUpTo(
           HomeScreen
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }


fun NavHostController.navigateAndClearBackStack(route: Destinations) =
    this.navigate(route) {
        popUpTo(
            0
        ) {
            inclusive = true
        }
    }


fun NavHostController.navigateAndPopUpTo(route: String, popUpTo: String) =
    this.navigate(route) {
        popUpTo(popUpTo)
    }


fun NavHostController.navigateToAndRemoveCurrent(route: Destinations) =
    this.navigate(route) {
        popUpTo(route) {
            inclusive = true
        }
    }


fun NavHostController.navigateBottomNavigation(route: Destinations) =
    this.navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }


@Composable
fun <T> NavController.OnResult(key: String, initialValue: T, result: (T) -> Unit) {
    val resultState = currentBackStackEntry?.savedStateHandle
        ?.getStateFlow(key, initialValue)?.collectAsState()

    LaunchedEffect(resultState) {
        resultState?.value?.let {
            result(it)
            currentBackStackEntry?.savedStateHandle?.remove<T>(key)
        }
    }
}

fun NavController.navigateAndClearStackTo(
    route: Destinations,
    base: Destinations,
    isInclusive: Boolean = false
) =
    this.navigate(route = route, navOptions = navOptions {
        popUpTo(base) {
            inclusive = isInclusive
        }
    })