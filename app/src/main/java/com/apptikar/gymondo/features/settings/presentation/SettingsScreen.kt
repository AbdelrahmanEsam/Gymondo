package com.apptikar.gymondo.features.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apptikar.gymondo.LocalNavController
import com.apptikar.gymondo.LocalNetworkStatus
import com.apptikar.gymondo.R
import com.apptikar.gymondo.core.utils.network.connectivity.ConnectivityObserver.Status
import com.apptikar.gymondo.core.designSystem.components.GymondoButton
import com.apptikar.gymondo.core.designSystem.components.GymondoTextField
import com.apptikar.gymondo.core.designSystem.components.LoadingPlaceholder
import com.apptikar.gymondo.core.designSystem.components.OfflinePlaceHolder


@Composable
fun SettingsScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val homeState = viewModel.state.collectAsStateWithLifecycle()
    SettingsScreen(modifier, onEvent = viewModel::onEvent, state = homeState)

    //todo handle adaptive layouts
}


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onEvent: (SettingsIntent) -> Unit,
    state: State<SettingsState>? = null,
) {
    val keyboardManager = LocalSoftwareKeyboardController.current
    val networkStatus = LocalNetworkStatus.current
    val navController = LocalNavController.current

    LazyColumn(
        modifier = modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        if (networkStatus != Status.Available && state?.value?.isLoading == false) {
            item {
                OfflinePlaceHolder()
            }
        } else {
            if (state?.value?.isLoading == true) {
                item {
                    LoadingPlaceholder(modifier = Modifier.fillParentMaxSize())
                }
            } else {
                item {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = stringResource(R.string.please_add_your_city),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    GymondoTextField(
                        value = state?.value?.cityName ?: "",
                        onValueChanged = {
                            onEvent(SettingsIntent.OnCityNameChanged(it))
                        },
                        onGo = {
                            keyboardManager?.hide()
                            onEvent(SettingsIntent.SaveCityName)
                            navController.navigateUp()
                        },
                        textStyle = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    GymondoButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            keyboardManager?.hide()
                            onEvent(SettingsIntent.SaveCityName)
                            navController.navigateUp()
                        },
                        text = stringResource(R.string.save),
                        isSelected = true
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(
        modifier = Modifier.fillMaxSize(),
        onEvent = {},
    )
}

