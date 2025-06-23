package com.apptikar.gymondo.features.home.presentation.onePageScreen

import androidx.lifecycle.ViewModel
import com.apptikar.gymondo.features.home.domain.model.response.ForecastDayModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class OneDayScreenDetailsViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<OneDayScreenState> =
        MutableStateFlow(OneDayScreenState())
    val state = _state.asStateFlow()


    fun onEvent(intent: OneDayDetailsIntent) {
        when (intent) {
            is OneDayDetailsIntent.SetDayDetails -> {
                setDayDetails(intent.dayDetails)
            }
        }
    }

    private fun setDayDetails(dayForeCastModel: ForecastDayModel) {

        //due to the limitation of the api it I can't get the average of some details
        // so I am taking the first hour of the day

        _state.update {
            it.copy(
                temperature = dayForeCastModel.day.avgTempC,
                feelsLike = (dayForeCastModel.hour.firstOrNull())?.feelsLikeC ?: 0.0,
                windSpeed = dayForeCastModel.day.maxWindKph,
                uvIndex = dayForeCastModel.day.uv,
                pressure = (dayForeCastModel.hour.firstOrNull())?.pressureIn ?: 0.0,
                rainPercentage = dayForeCastModel.day.dailyWillItRain,
                sunrise = dayForeCastModel.astro.sunrise,
                sunset = dayForeCastModel.astro.sunset,
                humidity = dayForeCastModel.day.avgHumidity.toString(),
                cloud = (dayForeCastModel.hour.firstOrNull()?.cloud ?: 0).toString(),
                weatherIcon = dayForeCastModel.day.condition.icon,
                dayHours = dayForeCastModel.hour,
                day = dayForeCastModel.date,
            )
        }
    }
}