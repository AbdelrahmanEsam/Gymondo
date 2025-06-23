package com.apptikar.gymondo.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.apptikar.gymondo.LocalNavController
import com.apptikar.gymondo.features.home.domain.model.response.ForecastDayModel
import com.apptikar.gymondo.features.home.presentation.DayDetailsCustomSerializerNavType
import com.apptikar.gymondo.features.home.presentation.homeScreen.HomeScreenRoot
import com.apptikar.gymondo.features.home.presentation.onePageScreen.OneDayDetailsScreenRoot
import com.apptikar.gymondo.features.settings.presentation.SettingsScreenRoot
import kotlin.reflect.typeOf


@Composable
fun GymondoNavGraph(
    modifier: Modifier,
) {

    NavHost(
        navController = LocalNavController.current,
        modifier = modifier,
        startDestination = Destinations.HomeScreen
    ) {

        composable<Destinations.HomeScreen> {
            HomeScreenRoot(
                modifier = Modifier
                    .fillMaxSize()

            )
        }


        composable<Destinations.OneDayDetailsScreen>(
            typeMap = mapOf(
                typeOf<ForecastDayModel>() to DayDetailsCustomSerializerNavType.dayDetailsType,
            )
        ) {
            val args = it.toRoute<Destinations.OneDayDetailsScreen>()
            OneDayDetailsScreenRoot(
                modifier = Modifier
                    .fillMaxSize(),
                dayModel = args.dayDetails
            )
        }

        composable<Destinations.SettingsScreen> {
            SettingsScreenRoot(
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}