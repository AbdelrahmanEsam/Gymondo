package com.apptikar.gymondo.features.home.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ConditionDto(
    val code: Int = 0,
    val icon: String = "",
    val text: String = ""
)