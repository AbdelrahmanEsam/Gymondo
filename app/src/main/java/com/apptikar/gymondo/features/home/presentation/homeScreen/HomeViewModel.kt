package com.apptikar.gymondo.features.home.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.gymondo.core.utils.SnackBarController
import com.apptikar.gymondo.core.utils.SnackBarErrorMessage
import com.apptikar.gymondo.core.utils.hiltAnnotations.Dispatcher
import com.apptikar.gymondo.core.utils.hiltAnnotations.HiltDispatchers
import com.apptikar.gymondo.core.utils.network.onError
import com.apptikar.gymondo.core.utils.network.onSuccess
import com.apptikar.gymondo.domain.usecases.GetUserCityNameUseCase
import com.apptikar.gymondo.features.home.domain.useCase.GetCityWeatherByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    @Dispatcher(HiltDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val getCityWeatherByNameUseCase: GetCityWeatherByNameUseCase,
    private val getUserCityNameUseCase: GetUserCityNameUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    fun onEvent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.GetCityWeatherByName -> {

                getCityWeatherByName(intent.cityName, indexToNumberOfDays(intent.indexNumberOfDays))
                _state.update { it.copy(numberOfDaysIndex = intent.indexNumberOfDays) }
            }

            is HomeIntent.GetCityByName -> {
                getUserCityName(intent.cityName)
            }
        }
    }

    private fun indexToNumberOfDays(selectedIndex: Int): Int {
        return when (selectedIndex) {
            1 -> 10
            else -> 1
        }
    }


    private fun getCityWeatherByName(cityName: String, numberOfDays: Int) {

        // I can get the 10 days from the start but to simulate it's has a different endpoint

        if ((state.value.forecastModel?.forecastDay?.size
                ?: 0) >= numberOfDays && cityName == state.value.cityName
        ) return
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(ioDispatcher) {
            getCityWeatherByNameUseCase.execute(cityName, numberOfDays)
                .collectLatest { weatherResult ->
                    weatherResult.onSuccess { cityWeatherResponseModel ->
                        _state.update {
                            it.copy(
                                temperature = cityWeatherResponseModel.currentWeather.tempC,
                                feelsLike = cityWeatherResponseModel.currentWeather.feelsLikeC,
                                windSpeed = cityWeatherResponseModel.currentWeather.windKph,
                                uvIndex = cityWeatherResponseModel.currentWeather.uv,
                                pressure = cityWeatherResponseModel.currentWeather.pressureIn,
                                humidity = cityWeatherResponseModel.currentWeather.humidity.toString(),
                                cloud = cityWeatherResponseModel.currentWeather.cloud.toString(),
                                forecastModel = cityWeatherResponseModel.forecast,
                                dayHours = cityWeatherResponseModel.forecast.forecastDay.firstOrNull()?.hour
                                    ?: emptyList(),
                                rainPercentage = cityWeatherResponseModel.forecast.forecastDay.firstOrNull()?.day?.dailyWillItRain
                                    ?: 0.0,
                                sunrise = cityWeatherResponseModel.forecast.forecastDay.firstOrNull()?.astro?.sunrise
                                    ?: "",
                                sunset = cityWeatherResponseModel.forecast.forecastDay.firstOrNull()?.astro?.sunset
                                    ?: "",
                                weatherIcon = cityWeatherResponseModel.currentWeather.condition.icon,
                                isLoading = false,
                            )
                        }
                    }.onError { error ->
                        SnackBarController.sendEvent(SnackBarErrorMessage(error))
                        _state.update {
                            it.copy(
                                isLoading = false,
                            )
                        }

                    }
                }
        }
    }

    private fun getUserCityName(cityKey: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(ioDispatcher) {
            getUserCityNameUseCase.execute(cityKey).collectLatest { result ->
                result.onSuccess { cityName ->
                    onEvent(HomeIntent.GetCityWeatherByName(cityName,_state.value.numberOfDaysIndex))
                    _state.update {
                        it.copy(
                            cityName = cityName,
                        )
                    }
                }.onError { error ->
                    SnackBarController.sendEvent(SnackBarErrorMessage(error))
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }


    init {
        getUserCityName("cityName")
    }
}