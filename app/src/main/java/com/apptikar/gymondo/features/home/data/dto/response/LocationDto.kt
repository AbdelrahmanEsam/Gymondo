package com.apptikar.gymondo.features.home.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val country: String,
    val lat: Double,
    val localtime: String,
    @SerialName("localtime_epoch")
    val localtimeEpoch: Int,
    val lon: Double,
    val name: String,
    val region: String,
    @SerialName("tz_id")
    val tzId: String
)