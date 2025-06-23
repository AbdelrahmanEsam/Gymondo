package com.apptikar.gymondo.features.home.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayDto(
    val astro: AstroDto,
    val date: String,
    @SerialName("date_epoch")
    val dateEpoch: Int,
    val day: DayDto,
    val hour: List<HourDto>
)