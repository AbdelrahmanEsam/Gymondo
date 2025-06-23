package com.apptikar.gymondo.data.datastore

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.apptikar.gymondo.R
import com.apptikar.gymondo.core.utils.network.Error
import com.apptikar.gymondo.core.utils.network.LocalError.DataStoreError
import com.apptikar.gymondo.core.utils.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class DataStoreUserPreferencesImpl @Inject constructor(private val context: Context) :
    DataStoreUserPreferences {



    override suspend fun putString(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun getString(key: String): Flow<Result<String, Error>> {


        val preferenceKey = stringPreferencesKey(key)
        return context.dataStore.data
            .catch {
                DataStoreError(errorMessage = it.message.toString())
            }
            .map { preference ->
                Result.Success(preference[preferenceKey] ?: context.getString(R.string.berlin))
            }
    }
}