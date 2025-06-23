package com.apptikar.gymondo.features.home.presentation.onePageScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apptikar.gymondo.LocalNavController
import com.apptikar.gymondo.LocalNetworkStatus
import com.apptikar.gymondo.R
import com.apptikar.gymondo.core.utils.network.connectivity.ConnectivityObserver.Status
import com.apptikar.gymondo.core.designSystem.components.GymondoAppBar
import com.apptikar.gymondo.core.designSystem.components.GymondoForecastCard
import com.apptikar.gymondo.core.designSystem.components.OfflinePlaceHolder
import com.apptikar.gymondo.core.designSystem.theme.whiteColor
import com.apptikar.gymondo.features.home.domain.model.response.ForecastDayModel
import com.apptikar.gymondo.features.home.presentation.components.HumidityAndCloudSection
import com.apptikar.gymondo.features.home.presentation.components.StatisticsSection
import com.apptikar.gymondo.features.home.presentation.components.SunriseAndSunSetSection


@Composable
fun OneDayDetailsScreenRoot(
    modifier: Modifier = Modifier,
    dayModel: ForecastDayModel,
    viewModel: OneDayScreenDetailsViewModel = hiltViewModel(),
) {

    LaunchedEffect(Unit) {
        viewModel.onEvent(OneDayDetailsIntent.SetDayDetails(dayModel))
    }


    val homeState = viewModel.state.collectAsStateWithLifecycle()
    OneDayDetailsScreen(modifier, onEvent = viewModel::onEvent, state = homeState)

    //todo handle adaptive layouts (if we are at bigger scale)
}


@Composable
fun OneDayDetailsScreen(
    modifier: Modifier = Modifier,
    onEvent: (OneDayDetailsIntent) -> Unit,
    state: State<OneDayScreenState>? = null,
) {
    val networkStatus = LocalNetworkStatus.current
    val navController = LocalNavController.current






    LazyColumn(
        modifier = modifier.systemBarsPadding().padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Top
    ) {
        if (networkStatus != Status.Available && state?.value?.isLoading == false) {
            item {
                OfflinePlaceHolder()
            }
        } else {
            item {
                state?.value?.day?.let {
                    GymondoAppBar(
                        modifier = Modifier.padding(start = 16.dp , end = 16.dp, bottom = 18.dp),
                        title = state.value.day,
                        trailingComposable = {
                            Image(
                                modifier = Modifier.align(Alignment.CenterStart).clickable {
                                    navController.navigateUp()
                                }, painter = painterResource(R.drawable.arrow_back), contentDescription = ""
                            )
                        }
                    )
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    AnimatedVisibility(state != null) {
                        StatisticsSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp , end = 16.dp, bottom = 18.dp),
                            windSpeed = state!!.value.windSpeed,
                            rainPercentage = state.value.rainPercentage,
                            pressure = state.value.pressure,
                            uvIndex = state.value.uvIndex
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    AnimatedVisibility(state?.value?.dayHours?.isNotEmpty() == true) {
                        GymondoForecastCard(
                            modifier = Modifier.padding(start = 16.dp , end = 16.dp, bottom = 18.dp),
                            title = stringResource(R.string.hourly_forecast),
                            icon = {
                                Image(
                                    modifier = Modifier
                                        .background(
                                            color = MaterialTheme.colorScheme.whiteColor,
                                            shape = CircleShape
                                        )
                                        .padding(4.dp),
                                    painter = painterResource(R.drawable.hourly),
                                    contentDescription = stringResource(R.string.hourly_forecast)
                                )
                            },
                            hourForecastList = state?.value?.dayHours ?: emptyList()
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))

                    AnimatedVisibility(state != null) {
                        SunriseAndSunSetSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp , end = 16.dp, bottom = 18.dp),
                            sunrise = state!!.value.sunrise,
                            sunset = state.value.sunset,
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))

                    AnimatedVisibility(state != null) {
                        HumidityAndCloudSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp , end = 16.dp, bottom = 18.dp),
                            humidity = state!!.value.humidity,
                            cloud = state.value.cloud,
                        )
                    }
                }
            }
        }
    }
}
