package com.apptikar.gymondo.features.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.gymondo.core.utils.SnackBarController
import com.apptikar.gymondo.core.utils.SnackBarErrorMessage
import com.apptikar.gymondo.core.utils.hiltAnnotations.Dispatcher
import com.apptikar.gymondo.core.utils.hiltAnnotations.HiltDispatchers
import com.apptikar.gymondo.core.utils.network.onError
import com.apptikar.gymondo.core.utils.network.onSuccess
import com.apptikar.gymondo.domain.usecases.GetUserCityNameUseCase
import com.apptikar.gymondo.features.home.presentation.homeScreen.HomeIntent
import com.apptikar.gymondo.features.settings.domain.usecase.SaveUserCityNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    @Dispatcher(HiltDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val saveUserCityNameUseCase: SaveUserCityNameUseCase,
    private val getUserCityNameUseCase: GetUserCityNameUseCase,
    ) : ViewModel() {
    private val _state: MutableStateFlow<SettingsState> =
        MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()


    fun onEvent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.OnCityNameChanged -> {
                onCityNameChange(intent.cityName)
            }

            SettingsIntent.SaveCityName -> {
                saveUserCityName(cityKey = "cityName",cityName = state.value.cityName)
            }
        }
    }

    private fun onCityNameChange(cityName : String)
    {
        _state.update { it.copy(cityName = cityName) }

    }

    private fun getUserCityName(cityKey: String): Job {
        _state.update { it.copy(isLoading = true) }
        return viewModelScope.launch(ioDispatcher) {
            getUserCityNameUseCase.execute(cityKey).collectLatest { result ->
                result.onSuccess { cityName ->
                    _state.update {
                        it.copy(
                            cityName = cityName,
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


    fun saveUserCityName(cityKey: String, cityName: String) {
        viewModelScope.launch(ioDispatcher) {
            saveUserCityNameUseCase.execute(cityKey, cityName)
        }
    }

    init {
        getUserCityName("cityName")
    }
}