package com.apptikar.gymondo.features.home.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherResponseDto(
    @SerialName("current")
    val currentDto: CurrentDto,
    @SerialName("forecast")
    val forecastDto: ForecastDto,
    @SerialName("location")
    val locationDto: LocationDto
)