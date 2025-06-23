package com.apptikar.gymondo.features.home.presentation.homeScreen

sealed interface HomeIntent {
    data class GetCityWeatherByName(val cityName: String, val indexNumberOfDays: Int = 0) : HomeIntent
    data class GetCityByName(val cityName: String) : HomeIntent
}