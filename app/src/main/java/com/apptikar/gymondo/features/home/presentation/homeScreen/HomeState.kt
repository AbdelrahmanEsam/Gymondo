package com.apptikar.gymondo.features.home.presentation.homeScreen

import androidx.compose.runtime.Immutable
import com.apptikar.gymondo.features.home.domain.model.response.ForecastModel
import com.apptikar.gymondo.features.home.domain.model.response.HourModel

@Immutable
data class HomeState(
    val temperature: Double = 0.0,
    val feelsLike: Double = 0.0,
    val windSpeed: Double = 0.0,
    val uvIndex: Double = 0.0,
    val pressure: Double = 0.0,
    val rainPercentage: Double = 0.0,
    val isLoading: Boolean = false,
    val forecastModel: ForecastModel? = null,
    val dayHours: List<HourModel> = emptyList(),
    val sunrise: String = "",
    val sunset: String = "",
    val humidity : String = "",
    val cloud : String = "",
    val weatherIcon : String = "",
    val cityName : String = "",
    val numberOfDaysIndex : Int = 0
)
