package com.apptikar.gymondo.core.utils.network

import kotlinx.serialization.Serializable

@Serializable
data class ResponseWrapperDto<T>(
    val data: T,
    val success: Boolean = false,
    val error: ErrorResponseDto? = null
)

@Serializable
data class ErrorResponseDto(
    val code: Int,
    val message: String
)