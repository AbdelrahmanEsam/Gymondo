package com.apptikar.gymondo


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.apptikar.gymondo.core.designSystem.components.bottomNavigation.GymondoBottomNavigation
import com.apptikar.gymondo.core.designSystem.theme.GymondoTheme
import com.apptikar.gymondo.core.utils.network.errorToMessageMapper
import com.apptikar.gymondo.core.utils.ObserveAsEvents
import com.apptikar.gymondo.core.utils.SnackBarController
import com.apptikar.gymondo.navigation.GymondoNavGraph
import kotlinx.coroutines.launch

@Composable
fun GymondoModal(snackBarHostState: SnackbarHostState) {
    GymondoTheme {
        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()
        val context = LocalContext.current

        ObserveAsEvents(
            flow = SnackBarController.events,
            snackBarHostState
        ) { event ->


            coroutineScope.launch {
                snackBarHostState.currentSnackbarData?.dismiss()
                val errorMessage = event.message.errorToMessageMapper()
                val result = snackBarHostState.showSnackbar(
                    message = errorMessage.errorMessage ?: context.getString(errorMessage.errorStringResource),
                    actionLabel = event.action?.name,
                    duration = SnackbarDuration.Long
                )

                if (result == SnackbarResult.ActionPerformed) {
                    event.action?.action?.invoke()
                }
            }
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Scaffold(
                snackbarHost = {
                    SnackbarHost(snackBarHostState)
                },
                bottomBar = {
                    GymondoBottomNavigation(navController)
                }
            ) { paddingValues ->
                Row(
                    Modifier
                        .fillMaxSize()
                ) {
                    CompositionLocalProvider(LocalNavController provides navController) {
                        GymondoNavGraph(
                            modifier = Modifier,
                        )
                    }
                }
            }
        }
    }

}