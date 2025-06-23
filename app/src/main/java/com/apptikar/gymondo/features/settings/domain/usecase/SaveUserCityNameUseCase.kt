package com.apptikar.gymondo.features.settings.domain.usecase

import com.apptikar.gymondo.features.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SaveUserCityNameUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    suspend fun execute(
        cityKey: String,
        cityName: String,
    ) = settingsRepository.saveCityName(cityKey, cityName)
}