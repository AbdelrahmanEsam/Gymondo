package com.apptikar.gymondo.features.home.data.dataSource.remote

import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.Result
import com.apptikar.gymondo.features.home.data.dto.response.CityWeatherResponseDto

interface HomeRemoteDataSource {

    suspend fun getCityWeatherByName(cityName: String,numberOfDays : Int): Result<CityWeatherResponseDto, Error>
}