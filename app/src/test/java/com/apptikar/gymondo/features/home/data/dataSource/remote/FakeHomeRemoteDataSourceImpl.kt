package com.apptikar.gymondo.features.home.data.dataSource.remote

import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.NetworkError.NotFound
import com.apptikar.gymondo.core.utils.network.Result
import com.apptikar.gymondo.features.home.data.dto.response.CityWeatherResponseDto

class FakeHomeRemoteDataSourceImpl(private val listOfWeatherDto: List<CityWeatherResponseDto>) :
    HomeRemoteDataSource {
    override suspend fun getCityWeatherByName(
        cityName: String,
        numberOfDays: Int,
    ): Result<CityWeatherResponseDto, Error> {
        val cityWeather = listOfWeatherDto.find { it.locationDto.name == cityName }
        return (cityWeather?.let {
            Result.Success(it)
        } ?: run {
            Result.Error(NotFound(errorMessage = "City not found"))
        })
    }
}