package com.apptikar.gymondo.features.settings.domain.repository

interface SettingsRepository {
    suspend fun saveCityName(cityKey: String, cityName: String)
}