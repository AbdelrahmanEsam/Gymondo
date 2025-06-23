package com.apptikar.gymondo.features.home.data.dataSource.local

import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.Result
import com.apptikar.gymondo.data.datastore.DataStoreUserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStoreUserPreferences,
) : HomeLocalDataSource {


    override suspend fun getCityPreference(cityKey: String): Flow<Result<String, Error>> {
        return dataStore.getString(cityKey)
    }
}