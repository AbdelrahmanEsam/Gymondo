package com.apptikar.gymondo.navigation

import com.apptikar.gymondo.features.home.domain.model.response.ForecastDayModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface Destinations {


    @Serializable
    object HomeScreen : Destinations

    @Serializable
    data class OneDayDetailsScreen(val dayDetails : ForecastDayModel) : Destinations

    @Serializable
    object SettingsScreen : Destinations
}