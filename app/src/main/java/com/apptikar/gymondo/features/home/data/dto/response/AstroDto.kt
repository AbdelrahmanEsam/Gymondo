package com.apptikar.gymondo.features.home.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AstroDto(
    @SerialName("is_moon_up")
    val isMoonUp: Int,
    @SerialName("is_sun_up")
    val isSunUp: Int,
    @SerialName("moon_illumination")
    val moonIllumination: Int,
    @SerialName("moon_phase")
    val moonPhase: String,
    val moonrise: String,
    @SerialName("moonset")
    val moonSet: String,
    val sunrise: String,
    val sunset: String
)