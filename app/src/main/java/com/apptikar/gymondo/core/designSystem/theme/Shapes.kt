package com.apptikar.gymondo.core.designSystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp


val roundedCorner14Shape = RoundedCornerShape(14.dp)
val roundedCorner16Shape = RoundedCornerShape(16.dp)
val roundedCorner18Shape = RoundedCornerShape(18.dp)
val roundedCorner100Shape = RoundedCornerShape(100.dp)
val roundedCorner50Shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)


val shapes = Shapes(
    extraSmall = roundedCorner14Shape,
    small = roundedCorner16Shape,
    medium = roundedCorner18Shape,
    large = roundedCorner50Shape,
    extraLarge = roundedCorner100Shape
)