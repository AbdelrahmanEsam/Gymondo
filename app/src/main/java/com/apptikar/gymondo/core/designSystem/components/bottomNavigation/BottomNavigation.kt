package com.apptikar.gymondo.core.designSystem.components.bottomNavigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.apptikar.gymondo.core.utils.navigation.navigateBottomNavigation

@Composable
fun GymondoBottomNavigation(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val bottomNavigationHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()


    val screens by remember {
        derivedStateOf {
            mutableListOf(
                BottomBarScreen.Home,
                BottomBarScreen.Settings,
            )
        }
    }

    NavigationBar(
        modifier = Modifier.height(bottomNavigationHeight + 50.dp),
        containerColor = MaterialTheme.colorScheme.secondary,
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navBackStackEntry = navBackStackEntry,
                onClick = {
                    navController.navigateBottomNavigation(screen.route)
                }
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    navBackStackEntry: NavBackStackEntry?,
    onClick: () -> Unit,
) {

    val selected by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.destination?.route?.contains(screen.route.toString()) == true
        }
    }

    NavigationBarItem(
        onClick = onClick,
        label = {
            Text(
                text = stringResource(screen.title),
                style = MaterialTheme.typography.labelSmall
            )
        },
        icon = {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(screen.icon),
                contentDescription = screen.route.toString(),
            )
        },
        selected = selected,
    )
}
