package com.apptikar.gymondo.core.designSystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryColor = Color(0xFF8A20D5)
val secondaryColor = Color(0xFFD0BCFF)
val darkPrimaryColor = Color(0xFF2E004E)
val backgroundColor = Color(0xFFF6EDFF)
val appBarColor = Color(0xFFE2D3FA)
val fullyWhiteColor = Color(0xFFFFFFFF)
val offWhiteColor = Color(0xFFFAEDFF)
val blackColor = Color(0xFF000000)
val darkBrownColor = Color(0xFF1E1B1B)
val naturalColor = Color(0xFF494649)
val twilightPurple = Color(0xFF6E48AA)
val nightBlue = Color(0xFF0D1B2A)


@get:Composable
val ColorScheme.buttonDisabledContainerColor: Color
    get() = fullyWhiteColor

@get:Composable
val ColorScheme.whiteColor: Color
    get() = fullyWhiteColor

@get:Composable
val ColorScheme.appBarBackgroundColor: Color
    get() = appBarColor


@get:Composable
val ColorScheme.buttonDisabledContentColor: Color
    get() = blackColor


@get:Composable
val ColorScheme.transparentColor: Color
    get() = Color.Transparent


@get:Composable
val ColorScheme.twilightPurpleColor: Color
    get() = twilightPurple

@get:Composable
val ColorScheme.nightBlueColor: Color
    get() = nightBlue
