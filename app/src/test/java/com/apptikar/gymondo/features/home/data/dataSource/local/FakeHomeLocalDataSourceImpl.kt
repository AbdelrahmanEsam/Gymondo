package com.apptikar.gymondo.features.home.data.dataSource.local

import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.LocalError.DataStoreError
import com.apptikar.gymondo.core.utils.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHomeLocalDataSourceImpl(private val mapStore: MutableMap<String, String>) :
    HomeLocalDataSource {
    override suspend fun getCityPreference(cityKey: String): Flow<Result<String, Error>> = flow {
        emit(try {
            Result.Success(mapStore[cityKey]!!)
        } catch (exception: Exception) {
            Result.Error(DataStoreError(errorMessage = exception.message.toString()))
        })
    }
}