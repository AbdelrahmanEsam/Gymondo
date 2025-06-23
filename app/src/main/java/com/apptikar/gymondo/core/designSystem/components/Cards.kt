package com.apptikar.gymondo.core.designSystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apptikar.gymondo.R
import com.apptikar.gymondo.features.home.domain.model.response.ForecastDayModel
import com.apptikar.gymondo.features.home.domain.model.response.HourModel


@Composable
fun GymondoStatisticCard(
    modifier: Modifier = Modifier,
    leadingComposable: @Composable (() -> Unit)? = null,
    title: String,
    subTitle: String,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .padding(
                    horizontal = 11.dp,
                    vertical = 9.dp
                ), verticalAlignment = Alignment.CenterVertically
        ) {
            leadingComposable?.invoke()
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                Text(text = title, style = MaterialTheme.typography.labelMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = subTitle, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Composable
fun TodayWeatherCard(
    modifier: Modifier = Modifier,
    dayModel: ForecastDayModel,
    onClick : (ForecastDayModel) ->  Unit
) {
    with(dayModel) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(120.dp).clickable{
                    onClick.invoke(dayModel)
                },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
            shape = MaterialTheme.shapes.medium,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = day.condition.text,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.temperature, day.maxTempC),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Text(
                            text = stringResource(R.string.temperature, day.minTempC),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                            .background(Color.Black.copy(alpha = 0.3f))
                    )

                    AppImageLoader(
                        modifier = Modifier.size(50.dp),
                        imageUrl = day.condition.icon,
                        contentDescription = stringResource(R.string.weather_image)
                    )
                }
            }
        }
    }
}


@Composable
fun GymondoForecastCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: @Composable (() -> Unit),
    hourForecastList: List<HourModel>,
) {

    @Composable
    fun CardHeader() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon.invoke()
            Spacer(modifier = Modifier.width(width = 8.dp))
            Text(text = title, style = MaterialTheme.typography.labelMedium)
        }
    }

    @Composable
    fun DayForecastItem(hourModel: HourModel) {
        Column(
            modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = hourModel.timeEpoch.toString(),
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            AppImageLoader(
                modifier = Modifier.size(24.dp),
                imageUrl = hourModel.condition.icon,
                contentDescription = stringResource(R.string.weather_image)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.temperature, hourModel.tempC),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            CardHeader()
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(hourForecastList, key = { it.hashCode() }) {
                    DayForecastItem(it)
                }
            }
        }
    }
}


@Preview
@Composable
private fun GymondoCardPreview() {
    GymondoStatisticCard(
        title = "Title",
        subTitle = "SubTitle"
    )
}

@Preview
@Composable
private fun GymondoCardWithLeadingPreview() {
    GymondoStatisticCard(
        title = "Title",
        subTitle = "SubTitle",
        leadingComposable = { Text(text = "Leading") }
    )
}

@Preview
@Composable
private fun GymondoCardWithTrailingPreview() {
    GymondoStatisticCard(
        title = "Title",
        subTitle = "SubTitle",
    )
}



