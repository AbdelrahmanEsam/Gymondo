package com.apptikar.gymondo.features.home.presentation.onePageScreen

import androidx.compose.runtime.Immutable
import com.apptikar.gymondo.features.home.domain.model.response.HourModel

@Immutable
data class OneDayScreenState(
    val temperature: Double = 0.0,
    val feelsLike: Double = 0.0,
    val windSpeed: Double = 0.0,
    val uvIndex: Double = 0.0,
    val pressure: Double = 0.0,
    val rainPercentage: Double = 0.0,
    val isLoading: Boolean = false,
    val dayHours: List<HourModel> = emptyList(),
    val sunrise: String = "",
    val sunset: String = "",
    val humidity : String = "",
    val cloud : String = "",
    val weatherIcon : String = "",
    val day : String = ""
)
