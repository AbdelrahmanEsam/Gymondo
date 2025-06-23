package com.apptikar.gymondo.features.settings.di

import com.apptikar.gymondo.data.datastore.DataStoreUserPreferences
import com.apptikar.gymondo.features.settings.data.local.SettingsLocalDataSource
import com.apptikar.gymondo.features.settings.data.local.SettingsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SettingsDataSourceModule {

    @Singleton
    @Provides
    fun provideDataStoreUserPreferences(dataStore: DataStoreUserPreferences): SettingsLocalDataSource =
        SettingsLocalDataSourceImpl(dataStore)

}