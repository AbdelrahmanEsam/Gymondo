package com.apptikar.gymondo.core.utils.network

import androidx.annotation.StringRes
import com.apptikar.gymondo.R


sealed interface NetworkError : Error {
    data object RequestTimeout : NetworkError
    data object Unauthorized : NetworkError
    data object Conflict : NetworkError
    data object NoInternet : NetworkError
    data object PayloadTooLarge : NetworkError
    data object ServerError : NetworkError
    data object Serialization : NetworkError
    data object Unknown : NetworkError
    data class BadRequest(val errorMessage: String?) : NetworkError
    data class NotFound(val errorMessage: String?) : NetworkError
    data class Unprocessable(val errorMessage: String?) : NetworkError
}

sealed interface LocalError : Error {
    data class DataStoreError(val errorMessage: String?) : LocalError

}


data class ErrorMessage(
    val errorMessage: String? = null,
    @StringRes val errorStringResource: Int,
)

fun Error.errorToMessageMapper(): ErrorMessage {
    return when (this) {
        is NetworkError.BadRequest -> ErrorMessage(
            errorMessage = errorMessage,
            errorStringResource = R.string.network_invalid_data
        )

        NetworkError.Unauthorized -> ErrorMessage(
            errorStringResource = R.string.network_invalid_data
        )

        NetworkError.Conflict, NetworkError.RequestTimeout -> ErrorMessage(
            errorStringResource = R.string.network_error_conflict
        )

        is NetworkError.Unprocessable -> ErrorMessage(
            errorMessage,
            errorStringResource = R.string.network_error_conflict
        )

        is NetworkError.NotFound -> ErrorMessage(
            errorMessage,
            errorStringResource = R.string.network_error_conflict
        )

        is LocalError.DataStoreError -> ErrorMessage(
            errorMessage,
            errorStringResource = R.string.error_unknown
        )

        else -> ErrorMessage(errorStringResource = R.string.error_unknown)
    }
}