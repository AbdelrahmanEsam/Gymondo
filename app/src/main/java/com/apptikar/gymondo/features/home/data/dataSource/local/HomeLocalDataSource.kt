package com.apptikar.gymondo.features.home.data.dataSource.local

import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.Result
import kotlinx.coroutines.flow.Flow

interface HomeLocalDataSource {
    suspend  fun getCityPreference(cityKey : String) : Flow<Result<String, Error>>
}