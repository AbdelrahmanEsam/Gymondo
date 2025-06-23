package com.apptikar.gymondo.features.home.data.repository

import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.Result
import com.apptikar.gymondo.core.utils.network.onError
import com.apptikar.gymondo.core.utils.network.onSuccess
import com.apptikar.gymondo.features.home.data.dataSource.local.HomeLocalDataSource
import com.apptikar.gymondo.features.home.data.dataSource.remote.HomeRemoteDataSource
import com.apptikar.gymondo.features.home.data.mappers.toCityWeatherResponseModel
import com.apptikar.gymondo.features.home.domain.model.response.CityWeatherResponseModel
import com.apptikar.gymondo.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource,
) : HomeRepository {
    override suspend fun getCityWeatherByName(
        cityName: String,
        numberOfDays: Int,
    ): Flow<Result<CityWeatherResponseModel, Error>> =
        flow {
            val result = homeRemoteDataSource.getCityWeatherByName(cityName, numberOfDays)

            result.onSuccess {
                emit(Result.Success(it.toCityWeatherResponseModel()))
            }.onError { error ->
                emit(Result.Error(error))
            }
        }


    override suspend fun getCityName(cityKey: String): Flow<Result<String, Error>> {
        return localDataSource.getCityPreference(cityKey)
    }
}