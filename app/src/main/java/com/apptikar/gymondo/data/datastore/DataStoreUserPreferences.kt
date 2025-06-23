package com.apptikar.gymondo.data.datastore

import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.Result
import kotlinx.coroutines.flow.Flow

interface DataStoreUserPreferences {

    suspend fun putString(key: String, value: String)
    suspend fun getString(key: String): Flow<Result<String, Error>>


}