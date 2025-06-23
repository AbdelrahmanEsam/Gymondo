package com.apptikar.gymondo.features.home.domain.model.response



data class CityWeatherResponseModel(
    val currentWeather: CurrentModel,
    val forecast: ForecastModel,
    val location: LocationModel
)