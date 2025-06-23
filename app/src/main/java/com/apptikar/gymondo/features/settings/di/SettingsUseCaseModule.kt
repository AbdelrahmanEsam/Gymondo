package com.apptikar.gymondo.features.settings.di

import com.apptikar.gymondo.features.settings.domain.repository.SettingsRepository
import com.apptikar.gymondo.features.settings.domain.usecase.SaveUserCityNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SettingsUseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideSaveUserCityUseCase(
        settingsRepository: SettingsRepository,
    ): SaveUserCityNameUseCase =
        SaveUserCityNameUseCase(settingsRepository)


}