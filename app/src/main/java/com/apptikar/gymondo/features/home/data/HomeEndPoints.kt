package com.apptikar.gymondo.features.home.data

import com.apptikar.gymondo.BuildConfig
import io.ktor.http.Url


sealed interface HomeEndPoints {

    data class GetCityWeatherByCityName(val url: String = BuildConfig.base_url + "current?") :
        HomeEndPoints

}