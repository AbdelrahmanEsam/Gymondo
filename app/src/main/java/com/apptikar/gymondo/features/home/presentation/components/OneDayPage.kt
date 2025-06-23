package com.apptikar.gymondo.features.home.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apptikar.gymondo.R
import com.apptikar.gymondo.core.designSystem.components.GymondoForecastCard
import com.apptikar.gymondo.core.designSystem.components.GymondoStatisticCard
import com.apptikar.gymondo.core.designSystem.theme.whiteColor
import com.apptikar.gymondo.features.home.presentation.homeScreen.HomeState


@Composable
fun LazyItemScope.OneDayPage(state: State<HomeState>? = null) {
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

    AnimatedVisibility(state != null) {
        SunriseAndSunSetSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp, bottom = 18.dp),
            sunrise = state!!.value.sunrise,
            sunset = state.value.sunset,
        )
    }
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

@Composable
internal fun SunriseAndSunSetSection(
    modifier: Modifier = Modifier,
    sunrise: String,
    sunset: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        GymondoStatisticCard(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.sunrise),
            subTitle = sunrise,
            leadingComposable = {
                Image(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.whiteColor,
                            shape = CircleShape
                        )
                        .padding(4.dp),
                    painter = painterResource(R.drawable.sunrise),
                    contentDescription = stringResource(R.string.sunrise)
                )
            }
        )

        GymondoStatisticCard(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.sunset),
            subTitle = sunset,
            leadingComposable = {
                Image(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.whiteColor,
                            shape = CircleShape
                        )
                        .padding(4.dp),
                    painter = painterResource(R.drawable.sunset),
                    contentDescription = stringResource(R.string.sunset)
                )
            }
        )
    }
}

@Composable
internal fun HumidityAndCloudSection(
    modifier: Modifier = Modifier,
    humidity: String,
    cloud: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        GymondoStatisticCard(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.humidity),
            subTitle = stringResource(R.string.percent, humidity),
            leadingComposable = {
                Image(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.whiteColor,
                            shape = CircleShape
                        )
                        .padding(4.dp)
                        .size(20.dp),
                    painter = painterResource(R.drawable.humidity),
                    contentDescription = stringResource(R.string.humidity)
                )
            }
        )

        GymondoStatisticCard(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.cloud),
            subTitle = cloud,
            leadingComposable = {
                Image(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.whiteColor,
                            shape = CircleShape
                        )
                        .padding(4.dp)
                        .size(20.dp),
                    painter = painterResource(R.drawable.cloud),
                    contentDescription = stringResource(R.string.cloud)
                )
            }
        )
    }
}

@Composable
internal fun StatisticsSection(
    modifier: Modifier = Modifier,
    windSpeed: Double,
    rainPercentage: Double,
    pressure: Double,
    uvIndex: Double,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            GymondoStatisticCard(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.wind_speed),
                subTitle = stringResource(R.string.km_h, windSpeed),
                leadingComposable = {
                    Image(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.whiteColor,
                                shape = CircleShape
                            )
                            .padding(4.dp),
                        painter = painterResource(R.drawable.air),
                        contentDescription = stringResource(R.string.wind_speed)
                    )
                }
            )

            GymondoStatisticCard(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.rain_chance),
                subTitle = stringResource(
                    R.string.percent,
                    rainPercentage.toString()
                ),
                leadingComposable = {
                    Image(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.whiteColor,
                                shape = CircleShape
                            )
                            .padding(4.dp),
                        painter = painterResource(R.drawable.rainy),
                        contentDescription = stringResource(R.string.rain_chance)
                    )
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            GymondoStatisticCard(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.pressure),
                subTitle = stringResource(R.string.hps, pressure),
                leadingComposable = {
                    Image(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.whiteColor,
                                shape = CircleShape
                            )
                            .padding(4.dp),
                        painter = painterResource(R.drawable.waves),
                        contentDescription = stringResource(R.string.pressure)
                    )
                }
            )
            GymondoStatisticCard(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.uv_index),
                subTitle = uvIndex.toString(),
                leadingComposable = {
                    Image(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.whiteColor,
                                shape = CircleShape
                            )
                            .padding(4.dp),
                        painter = painterResource(R.drawable.sun),
                        contentDescription = stringResource(R.string.uv_index)
                    )
                }
            )
        }
    }
}




