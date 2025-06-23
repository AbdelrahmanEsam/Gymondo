package com.apptikar.gymondo.features.home.domain.model.response


data class LocationModel(
    val country: String = "",
    val lat: Double = 0.0,
    val localtime: String = "",
    val localtimeEpoch: Int = 0,
    val lon: Double = 0.0,
    val name: String = "",
    val region: String = "",
    val tzId: String = ""
)