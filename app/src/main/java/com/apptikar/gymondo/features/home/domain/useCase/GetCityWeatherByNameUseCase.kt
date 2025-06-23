package com.apptikar.gymondo.features.home.domain.useCase

import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.Result
import com.apptikar.gymondo.core.utils.network.mapSuccess
import com.apptikar.gymondo.features.home.domain.model.response.CityWeatherResponseModel
import com.apptikar.gymondo.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityWeatherByNameUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun execute(
        cityName: String,
        numberOfDays: Int,
    ): Flow<Result<CityWeatherResponseModel, Error>> =
        homeRepository.getCityWeatherByName(cityName, numberOfDays).mapSuccess { weather ->
            weather.copy(
                forecast = weather.forecast.copy(
                    forecastDay = weather.forecast.forecastDay.map {
                        it.copy(
                            hour = it.hour.take(8)
                        )
                    }.take(numberOfDays.coerceAtLeast(1))
                )
            )
        }
}