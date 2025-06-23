package com.apptikar.gymondo.core.utils.network.connectivity

import androidx.annotation.StringRes
import com.apptikar.gymondo.R
import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    sealed class Status(@StringRes val message: Int) {
        object Available : Status(R.string.internet_connection_restored)
        object Unavailable : Status(R.string.network_no_internet)
        object Lost : Status(R.string.network_no_internet)
    }
}