package com.apptikar.gymondo.features.home.domain.model.response

import kotlinx.serialization.Serializable


@Serializable
data class DayModel(
    val avgHumidity: Int,
    val avgTempC: Double,
    val avgTempF: Double,
    val avgVisKm: Double,
    val avgVisMiles: Double,
    val condition: ConditionModel,
    val dailyChanceOfRain: Double,
    val dailyChanceOfSnow: Double,
    val dailyWillItRain: Double,
    val dailyWillItSnow: Double,
    val maxTempC: Double,
    val maxTempF: Double,
    val maxWindKph: Double,
    val maxWindMph: Double,
    val minTempC: Double,
    val minTempF: Double,
    val totalPrecipIn: Double,
    val totalPrecipMM: Double,
    val totalSnowCM: Double,
    val uv: Double,
)