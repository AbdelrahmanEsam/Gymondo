package com.apptikar.gymondo.features.home.data.dataSource.remote

import com.apptikar.gymondo.BuildConfig
import com.apptikar.gymondo.core.utils.network.safeRequest
import com.apptikar.gymondo.data.httpClient.GymondoHttpClient
import com.apptikar.gymondo.features.home.data.HomeEndPoints
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject
import com.apptikar.gymondo.core.utils.network.Result
import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.features.home.data.dto.response.CityWeatherResponseDto

class HomeRemoteDataSourceImpl @Inject constructor(private val gymondoHttpClient: GymondoHttpClient) :
    HomeRemoteDataSource {

    override suspend fun getCityWeatherByName(
        cityName: String,
        numberOfDays: Int,
    ): Result<CityWeatherResponseDto, Error> {
        return gymondoHttpClient.httpClient.safeRequest<CityWeatherResponseDto> {
            get(
                urlString = HomeEndPoints.GetCityWeatherByCityName().url,
            ) {
                parameter("key", BuildConfig.weather_api_key)
                parameter("q", cityName)
                parameter("days", numberOfDays)
            }
        }
    }

}