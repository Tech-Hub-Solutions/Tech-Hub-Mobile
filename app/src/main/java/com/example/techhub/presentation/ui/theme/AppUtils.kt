package com.example.techhub.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

@Composable
fun AppUtils(
    appDimensions: Dimensions,
    content: @Composable () -> Unit
) {
    val appDimensions = remember { appDimensions }

    CompositionLocalProvider(LocalAppDimensions provides appDimensions) {
        content()
    }

}

val LocalAppDimensions = compositionLocalOf {
    mediumDimensions
}