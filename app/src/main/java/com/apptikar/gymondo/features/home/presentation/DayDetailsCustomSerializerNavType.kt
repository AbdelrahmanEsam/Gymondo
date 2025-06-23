package com.apptikar.gymondo.features.home.presentation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.apptikar.gymondo.features.home.domain.model.response.ForecastDayModel
import kotlinx.serialization.json.Json

object DayDetailsCustomSerializerNavType {
    val dayDetailsType = object : NavType<ForecastDayModel>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): ForecastDayModel? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): ForecastDayModel {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: ForecastDayModel): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: ForecastDayModel) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}