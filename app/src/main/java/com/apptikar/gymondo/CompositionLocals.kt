package com.apptikar.gymondo

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.apptikar.gymondo.core.utils.network.connectivity.ConnectivityObserver


internal val LocalNavController = compositionLocalOf<NavHostController> { error("No NavController provided") }
internal val LocalNetworkStatus = compositionLocalOf<ConnectivityObserver.Status> { error("No NetworkStatus provided") }