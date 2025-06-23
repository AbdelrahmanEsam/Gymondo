package com.apptikar.gymondo.features.home.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayDto(
    @SerialName("avghumidity")
    val avgHumidity: Int,
    @SerialName("avgtemp_c")
    val avgTempC: Double,
    @SerialName("avgtemp_f")
    val avgTempF: Double,
    @SerialName("avgvis_km")
    val avgVisKm: Double,
    @SerialName("avgvis_miles")
    val avgVisMiles: Double,
    val condition: ConditionDto,
    @SerialName("daily_chance_of_rain")
    val dailyChanceOfRain: Double,
    @SerialName("daily_chance_of_snow")
    val dailyChanceOfSnow: Double,
    @SerialName("daily_will_it_rain")
    val dailyWillItRain: Double,
    @SerialName("daily_will_it_snow")
    val dailyWillItSnow: Double,
    @SerialName("maxtemp_c")
    val maxTempC: Double,
    @SerialName("maxtemp_f")
    val maxTempF: Double,
    @SerialName("maxwind_kph")
    val maxWindKph: Double,
    @SerialName("maxwind_mph")
    val maxWindMph: Double,
    @SerialName("mintemp_c")
    val minTempC: Double,
    @SerialName("mintemp_f")
    val minTempF: Double,
    @SerialName("totalprecip_in")
    val totalPrecipIn: Double,
    @SerialName("totalprecip_mm")
    val totalPrecipMM: Double,
    @SerialName("totalsnow_cm")
    val totalSnowCM: Double,
    val uv: Double,
)