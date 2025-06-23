package com.apptikar.gymondo.core.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun Long.epochToHour(): Int {
    return Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.currentSystemDefault()).hour
}

fun String.toDayNameWithMonth(): String {
    return try {
        val localDate = LocalDate.parse(this)
        val dayOfWeek = localDate.dayOfWeek
        val month = localDate.month
        "$dayOfWeek, $month"
    } catch (e: Exception) {
        "Invalid date"
    }
}