package com.apptikar.gymondo.features.settings.presentation

sealed interface SettingsIntent {
    data object SaveCityName : SettingsIntent
    data class OnCityNameChanged(val cityName : String) : SettingsIntent
}