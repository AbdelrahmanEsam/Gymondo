package com.apptikar.gymondo.features.home.presentation.onePageScreen

import com.apptikar.gymondo.features.home.domain.model.response.ForecastDayModel

sealed interface OneDayDetailsIntent {
    data class SetDayDetails(val dayDetails : ForecastDayModel) : OneDayDetailsIntent
}