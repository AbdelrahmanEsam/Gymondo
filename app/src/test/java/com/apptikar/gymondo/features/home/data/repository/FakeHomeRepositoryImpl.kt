package com.apptikar.gymondo.features.home.data.repository

import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.LocalError.DataStoreError
import com.apptikar.gymondo.core.utils.network.NetworkError.NotFound
import com.apptikar.gymondo.core.utils.network.Result
import com.apptikar.gymondo.features.home.data.dto.response.CityWeatherResponseDto
import com.apptikar.gymondo.features.home.data.mappers.toCityWeatherResponseModel
import com.apptikar.gymondo.features.home.domain.model.response.CityWeatherResponseModel
import com.apptikar.gymondo.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHomeRepositoryImpl(
    private val listOfWeatherDto: List<CityWeatherResponseDto>,
    private val mapStore: MutableMap<String, String>,
) : HomeRepository {

    override suspend fun getCityWeatherByName(
        cityName: String,
        numberOfDays: Int,
    ): Flow<Result<CityWeatherResponseModel, Error>> = flow {
        val cityWeather = listOfWeatherDto.find { it.locationDto.name == cityName }
        emit(cityWeather?.let {
            Result.Success(it.toCityWeatherResponseModel())
        } ?: run {
            Result.Error(NotFound(errorMessage = "City not found"))
        })
    }

    override suspend fun getCityName(cityKey: String): Flow<Result<String, Error>> = flow {
        emit(
            try {
                Result.Success(mapStore[cityKey]!!)
            } catch (exception: Exception) {
                Result.Error(DataStoreError(errorMessage = exception.message.toString()))
            }
        )
    }


}