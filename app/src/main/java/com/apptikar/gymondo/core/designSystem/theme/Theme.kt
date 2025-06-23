package com.apptikar.gymondo.core.designSystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    background = backgroundColor,
    tertiary = naturalColor,
    onSecondary = blackColor,
    primaryContainer = secondaryColor,
    onPrimaryContainer = darkPrimaryColor,
    surface = secondaryColor.copy(alpha = 0.3f),
    onSurface = darkBrownColor
)

@Composable
fun GymondoTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = shapes,
        content = content,
    )
}