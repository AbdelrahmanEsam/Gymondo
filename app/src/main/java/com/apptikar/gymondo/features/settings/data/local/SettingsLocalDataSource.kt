package com.apptikar.gymondo.features.settings.data.local

interface SettingsLocalDataSource {
    suspend  fun saveCityPreference(cityKey : String ,cityName : String)
}