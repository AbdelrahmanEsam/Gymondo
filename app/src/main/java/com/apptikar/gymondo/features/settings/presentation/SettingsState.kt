package com.apptikar.gymondo.features.settings.presentation

import androidx.compose.runtime.Immutable
import com.apptikar.gymondo.core.utils.network.ErrorMessage

@Immutable
data class SettingsState(
    val cityName : String = "",
    val errorMessage: ErrorMessage? = null,
    val isLoading: Boolean = false,
)
