package com.apptikar.gymondo.features.home.domain.model.response

import kotlinx.serialization.Serializable


@Serializable
data class ConditionModel(
    val code: Int,
    val icon: String,
    val text: String,
)
