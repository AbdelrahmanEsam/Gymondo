package com.apptikar.gymondo.features.home.data.mappers

import com.apptikar.gymondo.core.utils.epochToHour
import com.apptikar.gymondo.core.utils.toDayNameWithMonth
import com.apptikar.gymondo.features.home.data.dto.response.AstroDto
import com.apptikar.gymondo.features.home.data.dto.response.CityWeatherResponseDto
import com.apptikar.gymondo.features.home.data.dto.response.ConditionDto
import com.apptikar.gymondo.features.home.data.dto.response.CurrentDto
import com.apptikar.gymondo.features.home.data.dto.response.DayDto
import com.apptikar.gymondo.features.home.data.dto.response.ForecastDayDto
import com.apptikar.gymondo.features.home.data.dto.response.ForecastDto
import com.apptikar.gymondo.features.home.data.dto.response.HourDto
import com.apptikar.gymondo.features.home.data.dto.response.LocationDto
import com.apptikar.gymondo.features.home.domain.model.response.AstroModel
import com.apptikar.gymondo.features.home.domain.model.response.CityWeatherResponseModel
import com.apptikar.gymondo.features.home.domain.model.response.ConditionModel
import com.apptikar.gymondo.features.home.domain.model.response.CurrentModel
import com.apptikar.gymondo.features.home.domain.model.response.DayModel
import com.apptikar.gymondo.features.home.domain.model.response.ForecastDayModel
import com.apptikar.gymondo.features.home.domain.model.response.ForecastModel
import com.apptikar.gymondo.features.home.domain.model.response.HourModel
import com.apptikar.gymondo.features.home.domain.model.response.LocationModel


fun CityWeatherResponseDto.toCityWeatherResponseModel(): CityWeatherResponseModel {
    return CityWeatherResponseModel(
        currentWeather = currentDto.toCurrentModel(),
        forecast = forecastDto.toForecastModel(),
        location = locationDto.toLocationModel()
    )
}

fun ForecastDto.toForecastModel(): ForecastModel {
    return ForecastModel(
        forecastDay = forecastDay.map { it.toForecastDayModel() }
    )
}

fun ForecastDayDto.toForecastDayModel(): ForecastDayModel {
    return ForecastDayModel(
        astro = astro.toAstroModel(),
        date = date.toDayNameWithMonth(),
        dateEpoch = dateEpoch,
        day = day.toDayModel(),
        hour = hour.map { it.toHourModel() }
    )
}


fun DayDto.toDayModel(): DayModel {
    return DayModel(
        avgHumidity = avgHumidity,
        avgTempC = avgTempC,
        avgTempF = avgTempF,
        avgVisKm = avgVisKm,
        avgVisMiles = avgVisMiles,
        condition = condition.toConditionModel(),
        dailyChanceOfRain = dailyChanceOfRain,
        dailyChanceOfSnow = dailyChanceOfSnow,
        dailyWillItRain = dailyWillItRain,
        dailyWillItSnow = dailyWillItSnow,
        maxTempC = maxTempC,
        maxTempF = maxTempF,
        maxWindKph = maxWindKph,
        maxWindMph = maxWindMph,
        minTempC = minTempC,
        minTempF = minTempF,
        totalPrecipIn = totalPrecipIn,
        totalPrecipMM = totalPrecipMM,
        totalSnowCM = totalSnowCM,
        uv = uv
    )
}

fun AstroDto.toAstroModel(): AstroModel {
    return AstroModel(
        isMoonUp = isMoonUp,
        isSunUp = isSunUp,
        moonIllumination = moonIllumination,
        moonPhase = moonPhase,
        moonrise = moonrise,
        moonSet = moonSet,
        sunrise = sunrise,
        sunset = sunset
    )
}

fun HourDto.toHourModel(): HourModel {
    return HourModel(
        chanceOfRain = chanceOfRain,
        chanceOfSnow = chanceOfSnow,
        cloud = cloud,
        condition = condition.toConditionModel(),
        dewPointC = dewPointC,
        dewPointF = dewPointF,
        feelsLikeC = feelsLikeC,
        feelsLikeF = feelsLikeF,
        gustKph = gustKph,
        gustMph = gustMph,
        heatIndexC = heatIndexC,
        heatIndexF = heatIndexF,
        humidity = humidity,
        isDay = isDay,
        precipIn = precipIn,
        precipMm = precipMm,
        pressureIn = pressureIn,
        pressureMb = pressureMb,
        snowCm = snowCm,
        tempC = tempC.toInt(),
        tempF = tempF,
        time = time.toDayNameWithMonth(),
        timeEpoch = timeEpoch.epochToHour(),
        uv = uv,
        visKm = visKm,
        visMiles = visMiles,
        willItRain = willItRain,
        willItSnow = willItSnow,
        windDegree = windDegree,
        windDir = windDir,
        windKph = windKph,
        windMph = windMph,
        windchillC = windchillC,
        windchillF = windchillF,
    )
}


fun CurrentDto.toCurrentModel(): CurrentModel {
    return CurrentModel(
        cloud = cloud,
        condition = condition.toConditionModel(),
        dewPointC = dewPointC,
        dewPointF = dewPointF,
        feelsLikeC = feelsLikeC,
        feelsLikeF = feelsLikeF,
        gustKph = gustKph,
        gustMph = gustMph,
        heatIndexC = heatIndexC,
        heatIndexF = heatIndexF,
        humidity = humidity,
        isDay = isDay,
        lastUpdated = lastUpdated,
        lastUpdatedEpoch = lastUpdatedEpoch,
        precipIn = precipIn,
        precipMm = precipMm,
        pressureIn = pressureIn,
        pressureMb = pressureMb,
        tempC = tempC,
        tempF = tempF,
        uv = uv,
        visKm = visKm,
        visMiles = visMiles,
        windDegree = windDegree,
        windDir = windDir,
        windKph = windKph,
        windMph = windMph,
        windchillC = windchillC,
        windchillF = windchillF,
    )
}


fun ConditionDto.toConditionModel(): ConditionModel {
    return ConditionModel(
        code = code,
        icon = "https:$icon",
        text = text,
    )
}

fun LocationDto.toLocationModel(): LocationModel {
    return LocationModel(
        country = country,
        lat = lat,
        localtime = localtime,
        localtimeEpoch = localtimeEpoch,
        lon = lon,
        name = name,
        region = region,
        tzId = tzId
    )
}
