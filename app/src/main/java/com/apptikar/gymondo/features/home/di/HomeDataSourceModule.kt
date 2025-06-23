package com.apptikar.gymondo.features.home.di

import com.apptikar.gymondo.data.datastore.DataStoreUserPreferences
import com.apptikar.gymondo.data.httpClient.GymondoHttpClient
import com.apptikar.gymondo.features.home.data.dataSource.local.HomeLocalDataSource
import com.apptikar.gymondo.features.home.data.dataSource.local.HomeLocalDataSourceImpl
import com.apptikar.gymondo.features.home.data.dataSource.remote.HomeRemoteDataSource
import com.apptikar.gymondo.features.home.data.dataSource.remote.HomeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HomeDataSourceModule {

    @Singleton
    @Provides
    fun provideHomeRemoteDataSource(gymondoHttpClient: GymondoHttpClient): HomeRemoteDataSource =
        HomeRemoteDataSourceImpl(gymondoHttpClient)


    @Singleton
    @Provides
    fun provideDataStoreUserPreferences(dataStore: DataStoreUserPreferences): HomeLocalDataSource =
        HomeLocalDataSourceImpl(dataStore)

}