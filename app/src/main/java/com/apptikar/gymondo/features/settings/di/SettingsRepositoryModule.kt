package com.apptikar.gymondo.features.settings.di

import com.apptikar.gymondo.features.settings.data.local.SettingsLocalDataSource
import com.apptikar.gymondo.features.settings.data.repository.SettingsRepositoryImpl
import com.apptikar.gymondo.features.settings.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsRepositoryModule {

    @Provides
    @Singleton
    fun provideSettingsRepository(
        homeLocalDataSource: SettingsLocalDataSource,
    ): SettingsRepository =
        SettingsRepositoryImpl( homeLocalDataSource)
}