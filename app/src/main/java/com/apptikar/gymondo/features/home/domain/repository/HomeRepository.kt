package com.apptikar.gymondo.features.home.domain.repository

import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.Result
import com.apptikar.gymondo.features.home.domain.model.response.CityWeatherResponseModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getCityWeatherByName(cityName: String,numberOfDays : Int): Flow<Result<CityWeatherResponseModel, Error>>


    suspend  fun getCityName(cityKey : String) : Flow<Result<String, Error>>
}