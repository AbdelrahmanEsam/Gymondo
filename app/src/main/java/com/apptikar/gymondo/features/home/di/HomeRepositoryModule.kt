package com.apptikar.gymondo.features.home.di

import com.apptikar.gymondo.features.home.data.dataSource.local.HomeLocalDataSource
import com.apptikar.gymondo.features.home.data.dataSource.remote.HomeRemoteDataSource
import com.apptikar.gymondo.features.home.data.repository.HomeRepositoryImpl
import com.apptikar.gymondo.features.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        homeRemoteDataSource: HomeRemoteDataSource,
        homeLocalDataSource: HomeLocalDataSource,
    ): HomeRepository =
        HomeRepositoryImpl(homeRemoteDataSource, homeLocalDataSource)
}