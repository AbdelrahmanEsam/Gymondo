package com.apptikar.gymondo.features.home.domain.model.response


data class ForecastModel(
    val forecastDay: List<ForecastDayModel> = listOf()
)