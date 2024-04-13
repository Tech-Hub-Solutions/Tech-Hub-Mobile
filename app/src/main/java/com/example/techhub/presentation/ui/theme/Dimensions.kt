package com.example.techhub.presentation.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val extraSmall: Dp = 0.dp,
    val small1: Dp = 0.dp,
    val small2: Dp = 0.dp,
    val small3: Dp = 0.dp,
    val medium1: Dp = 0.dp,
    val medium2: Dp = 0.dp,
    val medium3: Dp = 0.dp,
    val large: Dp = 0.dp,
    val buttonHeight: Dp = 60.dp,
    val buttonWidth: Dp = 350.dp,
    val centeredImageWidthMedium: Dp = 110.dp,
    val centeredImageHeightMedium: Dp = 20.dp,
    val centeredImageWidthLarge: Dp = 0.dp,
    val centeredImageHeightLarge: Dp = 0.dp,
    val logoWidth: Dp = 110.dp,
    val logoHeight: Dp = 20.dp
)

val smallDimensions = Dimensions(
    small1 = 6.dp,
    small2 = 5.dp,
    small3 = 8.dp,
    medium1 = 15.dp,
    medium2 = 26.dp,
    medium3 = 30.dp,
    large = 45.dp,
    buttonHeight = 30.dp,
    buttonWidth = 175.dp,
    centeredImageWidthLarge = 252.dp,
    centeredImageHeightLarge = 300.dp,
    logoWidth = 94.dp,
    logoHeight = 17.dp
)

val compactDimensions = Dimensions(
    small1 = 8.dp,
    small2 = 13.dp,
    small3 = 17.dp,
    medium1 = 25.dp,
    medium2 = 30.dp,
    medium3 = 35.dp,
    large = 65.dp,
    centeredImageWidthLarge = 20.dp,
    centeredImageHeightLarge = 20.dp,
)

val mediumDimensions = Dimensions(
    small1 = 10.dp,
    small2 = 15.dp,
    small3 = 20.dp,
    medium1 = 30.dp,
    medium2 = 36.dp,
    medium3 = 40.dp,
    large = 110.dp,
    centeredImageWidthLarge = 252.dp,
    centeredImageHeightLarge = 300.dp,
    logoWidth = 144.dp,
    logoHeight = 26.dp,
)

val largeDimensions = Dimensions(
    small1 = 15.dp,
    small2 = 20.dp,
    small3 = 25.dp,
    medium1 = 35.dp,
    medium2 = 30.dp,
    medium3 = 45.dp,
    large = 130.dp,
    logoWidth = 189.dp,
    logoHeight = 34.dp
)