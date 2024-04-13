package com.example.techhub.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.techhub.presentation.index.IndexActivity

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun TechHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
    
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val orientation = when {
        rememberWindowSizeClass().width.size > rememberWindowSizeClass().height.size -> Orientation.Landscape
        else -> Orientation.Portrait
    }

    val sizeThatMatters = when (orientation) {
        Orientation.Portrait -> rememberWindowSizeClass().width
        else -> rememberWindowSizeClass().height
    }

    val dimensions = when (sizeThatMatters) {
        is WindowSize.Small -> smallDimensions
        is WindowSize.Compact -> mediumDimensions
        is WindowSize.Medium -> mediumDimensions
        else -> largeDimensions
    }

    AppUtils(appDimensions = dimensions) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}

val MaterialTheme.dimensions
    @Composable
    get() = LocalAppDimensions.current

/*
*
    val window = calculateWindowSizeClass(activity = activity)
    val configuration = LocalConfiguration.current

    var typography: Typography
    var appDimensions: Dimensions

    when (window.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (configuration.screenWidthDp <= 360) {
                appDimensions = smallDimensions
                typography = smallTypography
            } else if (configuration.screenWidthDp < 480) {
                appDimensions = compactDimensions
                typography = compactTypography
            } else if (configuration.screenWidthDp < 720) {
                appDimensions = mediumDimensions
                typography = mediumTypography
            } else {
                appDimensions = largeDimensions
                typography = largeTypography
            }
        }

        WindowWidthSizeClass.Medium -> {
            appDimensions = mediumDimensions
            typography = mediumTypography
        }

        else -> {
            appDimensions = largeDimensions
            typography = largeTypography
        }
    }

*
* */