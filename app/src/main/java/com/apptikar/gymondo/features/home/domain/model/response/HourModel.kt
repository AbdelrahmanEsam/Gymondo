package com.apptikar.gymondo.features.home.domain.model.response

import kotlinx.serialization.Serializable


@Serializable
data class HourModel(
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val cloud: Int,
    val condition: ConditionModel,
    val dewPointC: Double,
    val dewPointF: Double,
    val feelsLikeC: Double,
    val feelsLikeF: Double,
    val gustKph: Double,
    val gustMph: Double,
    val heatIndexC: Double,
    val heatIndexF: Double,
    val humidity: Int,
    val isDay: Int,
    val precipIn: Double,
    val precipMm: Double,
    val pressureIn: Double,
    val pressureMb: Double,
    val snowCm: Double,
    val tempC: Int,
    val tempF: Double,
    val time: String,
    val timeEpoch: Int,
    val uv: Double,
    val visKm: Double,
    val visMiles: Double,
    val willItRain: Int,
    val willItSnow: Int,
    val windDegree: Int,
    val windDir: String,
    val windKph: Double,
    val windMph: Double,
    val windchillC: Double,
    val windchillF: Double,
)
