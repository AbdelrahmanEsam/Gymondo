package com.apptikar.gymondo.features.home.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class HourDto(
    @SerialName("chance_of_rain")
    val chanceOfRain: Int,
    @SerialName("chance_of_snow")
    val chanceOfSnow: Int,
    val cloud: Int,
    val condition: ConditionDto,
    @SerialName("dewpoint_c")
    val dewPointC: Double,
    @SerialName("dewpoint_f")
    val dewPointF: Double,
    @SerialName("feelslike_c")
    val feelsLikeC: Double,
    @SerialName("feelslike_f")
    val feelsLikeF: Double,
    @SerialName("gust_kph")
    val gustKph: Double,
    @SerialName("gust_mph")
    val gustMph: Double,
    @SerialName("heatindex_c")
    val heatIndexC: Double,
    @SerialName("heatindex_f")
    val heatIndexF: Double,
    val humidity: Int,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("precip_in")
    val precipIn: Double,
    @SerialName("precip_mm")
    val precipMm: Double,
    @SerialName("pressure_in")
    val pressureIn: Double,
    @SerialName("pressure_mb")
    val pressureMb: Double,
    @SerialName("snow_cm")
    val snowCm: Double,
    @SerialName("temp_c")
    val tempC: Double,
    @SerialName("temp_f")
    val tempF: Double,
    val time: String,
    @SerialName("time_epoch")
    val timeEpoch: Long,
    val uv: Double,
    @SerialName("vis_km")
    val visKm: Double,
    @SerialName("vis_miles")
    val visMiles: Double,
    @SerialName("will_it_rain")
    val willItRain: Int,
    @SerialName("will_it_snow")
    val willItSnow: Int,
    @SerialName("wind_degree")
    val windDegree: Int,
    @SerialName("wind_dir")
    val windDir: String,
    @SerialName("wind_kph")
    val windKph: Double,
    @SerialName("wind_mph")
    val windMph: Double,
    @SerialName("windchill_c")
    val windchillC: Double,
    @SerialName("windchill_f")
    val windchillF: Double
)