package com.apptikar.gymondo.features.settings.data.repository

import com.apptikar.gymondo.features.settings.data.local.SettingsLocalDataSource
import com.apptikar.gymondo.features.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val localDataSource: SettingsLocalDataSource,
) : SettingsRepository {
    override suspend fun saveCityName(cityKey: String, cityName: String) {
        localDataSource.saveCityPreference(cityKey, cityName)
    }
}
