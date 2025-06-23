package com.apptikar.gymondo.features.settings.data.local

import com.apptikar.gymondo.data.datastore.DataStoreUserPreferences
import javax.inject.Inject

class SettingsLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStoreUserPreferences,
) : SettingsLocalDataSource {
    override suspend fun saveCityPreference(cityKey: String, cityName: String) {
        dataStore.putString(cityKey, cityName)
    }
}