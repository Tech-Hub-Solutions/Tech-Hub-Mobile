package com.example.techhub.common.composable

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.memory.MemoryCache


@Composable
fun LoadableAsyncImage(
    modifier: Modifier = Modifier,
    model: Any?,
    contentDescription: String?,
    placeholderMemoryCacheKey: String? = null,
    loadingIndicatorSize: Dp = 40.dp,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val imageLoader = LocalContext.current.imageLoader
    var placeholderBitmap by remember(placeholderMemoryCacheKey) { mutableStateOf<Bitmap?>(null) }
    var isLoading by rememberSaveable(model) { mutableStateOf(true) }

    LaunchedEffect(placeholderMemoryCacheKey) {
        placeholderMemoryCacheKey?.let {
            placeholderBitmap =
                imageLoader.memoryCache?.get(MemoryCache.Key(placeholderMemoryCacheKey))?.bitmap
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = model,
            contentDescription = contentDescription,
            contentScale = contentScale,
            onSuccess = { isLoading = false },
        )

        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            if (placeholderBitmap == null) {
                LoadingIndicator(
                    size = loadingIndicatorSize,
                )
            } else {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    bitmap = (placeholderBitmap as Bitmap).asImageBitmap(),
                    contentDescription = contentDescription,
                    contentScale = contentScale,
                )
            }
        }
    }
}

@Composable
fun LoadingIndicator(size: Dp) {
    TODO("Not yet implemented")
}
