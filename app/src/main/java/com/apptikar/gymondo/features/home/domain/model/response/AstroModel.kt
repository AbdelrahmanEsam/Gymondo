package com.apptikar.gymondo.features.home.domain.model.response

import android.os.Parcelable
import kotlinx.serialization.Serializable


@Serializable
data class AstroModel(
    val isMoonUp: Int,
    val isSunUp: Int,
    val moonIllumination: Int,
    val moonPhase: String,
    val moonrise: String,
    val moonSet: String,
    val sunrise: String,
    val sunset: String
)