package com.apptikar.gymondo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.apptikar.gymondo.core.utils.network.connectivity.ConnectivityObserver
import com.apptikar.gymondo.core.designSystem.theme.GymondoTheme
import com.apptikar.gymondo.core.utils.hiltAnnotations.Dispatcher
import com.apptikar.gymondo.core.utils.hiltAnnotations.HiltDispatchers
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    @Inject
    @Dispatcher(HiltDispatchers.IO)
    lateinit var ioDispatcher: CoroutineDispatcher


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var networkStatus: MutableState<ConnectivityObserver.Status> =
                remember { mutableStateOf(ConnectivityObserver.Status.Lost) }

            val snackBarHostState = remember { SnackbarHostState() }

            LaunchedEffect(Unit) {
                connectivityObserver.observe().collectLatest { status ->
                    networkStatus.value = status
                    if (status != networkStatus.value) {
                        snackBarHostState.currentSnackbarData?.dismiss()
                        snackBarHostState.showSnackbar(
                            message = getString(status.message),
                            duration = SnackbarDuration.Long
                        )
                    }

                }
            }

            CompositionLocalProvider(LocalNetworkStatus provides networkStatus.value) {
                GymondoModal(snackBarHostState)
            }
        }

    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GymondoTheme {
        Greeting("Android")
    }
}