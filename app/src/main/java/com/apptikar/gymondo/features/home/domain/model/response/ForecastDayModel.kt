package com.apptikar.gymondo.features.home.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayModel(
    val astro: AstroModel,
    val date: String,
    val dateEpoch: Int,
    val day: DayModel,
    val hour: List<HourModel>,
)