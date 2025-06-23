package com.apptikar.gymondo.features.home.di

import com.apptikar.gymondo.features.home.domain.repository.HomeRepository
import com.apptikar.gymondo.features.home.domain.useCase.GetCityWeatherByNameUseCase
import com.apptikar.gymondo.domain.usecases.GetUserCityNameUseCase
import com.apptikar.gymondo.features.settings.domain.usecase.SaveUserCityNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object HomeUseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideCityWeatherByNameUseCase(
        homeRepository: HomeRepository,
    ): GetCityWeatherByNameUseCase =
        GetCityWeatherByNameUseCase(homeRepository)

    @Provides
    @ViewModelScoped
    fun provideGetUserCityUseCase(
        homeRepository: HomeRepository,
    ): GetUserCityNameUseCase =
        GetUserCityNameUseCase(homeRepository)
}