package com.apptikar.gymondo.features.home.presentation.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apptikar.gymondo.LocalNavController
import com.apptikar.gymondo.LocalNetworkStatus
import com.apptikar.gymondo.R
import com.apptikar.gymondo.core.utils.network.connectivity.ConnectivityObserver.Status
import com.apptikar.gymondo.core.designSystem.components.AppImageLoader
import com.apptikar.gymondo.core.designSystem.components.GymondoSegmentedControl
import com.apptikar.gymondo.core.designSystem.components.LoadingPlaceholder
import com.apptikar.gymondo.core.designSystem.components.OfflinePlaceHolder
import com.apptikar.gymondo.core.designSystem.components.TodayWeatherCard
import com.apptikar.gymondo.core.designSystem.theme.appBarBackgroundColor
import com.apptikar.gymondo.features.home.presentation.components.OneDayPage
import com.apptikar.gymondo.features.home.presentation.homeScreen.HomeIntent.GetCityWeatherByName
import com.apptikar.gymondo.navigation.Destinations


@Composable
fun HomeScreenRoot(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    val homeState = viewModel.state.collectAsStateWithLifecycle()


    HomeScreen(modifier, onEvent = viewModel::onEvent, state = homeState)

    //todo handle adaptive layouts
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onEvent: (HomeIntent) -> Unit,
    state: State<HomeState>? = null,
) {
    val networkStatus = LocalNetworkStatus.current
    val navController = LocalNavController.current
    val segmentOptions = stringArrayResource(R.array.segmented_control)
    val columnState = rememberLazyListState()
    val segmentOptionsState = remember(segmentOptions) { segmentOptions }
    var selectedSegmentIndex = rememberSaveable { mutableIntStateOf(0) }


    LazyColumn(
        modifier = modifier.navigationBarsPadding().padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Top,
        state = columnState
    ) {
        if (networkStatus != Status.Available && state?.value?.isLoading == false) {
            item {
                OfflinePlaceHolder()
            }
        } else {
            stickyHeader {
                AppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    location = state?.value?.cityName.toString(),
                    temperature = state?.value?.temperature.toString(),
                    feelsLike = state?.value?.feelsLike.toString(),
                    weatherIcon = state?.value?.weatherIcon.toString()
                )
                Spacer(modifier = Modifier.height(18.dp))
            }

            item {
                GymondoSegmentedControl(
                    modifier = Modifier.fillMaxWidth(),
                    options = segmentOptionsState,
                    selectedIndex = selectedSegmentIndex.intValue,
                    onSelectionChange = { index ->
                        selectedSegmentIndex.intValue = index
                        state?.let {
                            onEvent(GetCityWeatherByName(state.value.cityName, index))
                        }
                    }
                )
                Spacer(modifier = Modifier.height(18.dp))
            }

            if (state?.value?.isLoading == true) {
                item {
                    LoadingPlaceholder()
                }
            } else {
                if (selectedSegmentIndex.intValue == 0) {
                    item {
                        Column(
                            Modifier
                                .padding(horizontal = 16.dp)
                        ) {
                            OneDayPage(state = state)
                        }
                    }
                } else {
                    items(
                        state?.value?.forecastModel?.forecastDay ?: emptyList(),
                        key = { item ->
                            item.hashCode()
                        }) { item ->
                        TodayWeatherCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 16.dp),
                            dayModel = item
                        ) { dayDetailsModel ->
                            navController.navigate(Destinations.OneDayDetailsScreen(dayDetailsModel))
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    location: String = "Kharkiv, Ukraine",
    temperature: String,
    feelsLike: String,
    weatherIcon: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.appBarBackgroundColor
        ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    text = location,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = stringResource(R.string.temperature, temperature),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.feels_like_temperature, feelsLike),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            AppImageLoader(
                modifier = Modifier.size(100.dp),
                imageUrl = weatherIcon,
                contentDescription = stringResource(R.string.weather_image)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        onEvent = {},
    )
}

