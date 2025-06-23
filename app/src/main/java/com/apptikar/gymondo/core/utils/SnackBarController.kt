package com.apptikar.gymondo.core.utils

import com.apptikar.gymondo.core.utils.network.Error
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


data class SnackBarErrorMessage(val message: Error, val action: SnackBarAction? = null)


data class SnackBarAction(val name: String, val action: () -> Unit)


object SnackBarController {
    private val _events = Channel<SnackBarErrorMessage>()
    val events = _events.receiveAsFlow()
    suspend fun sendEvent(event: SnackBarErrorMessage) {
        _events.send(event)
    }
}