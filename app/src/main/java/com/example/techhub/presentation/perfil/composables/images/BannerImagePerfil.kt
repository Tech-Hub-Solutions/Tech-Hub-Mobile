package com.example.techhub.presentation.perfil.composables.images

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.techhub.R
import com.example.techhub.common.composable.CircularProgressIndicatorTH
import com.example.techhub.common.utils.UiText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun BannerImagePerfil(
    imagePath: String?,
    isLoading: Boolean
) {
    val context = LocalContext.current

    Row {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .height(110.dp)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicatorTH()
            }
        } else {
            if (imagePath == null) {
                Spacer(
                    modifier = Modifier
                        .background(PrimaryBlue)
                        .height(110.dp)
                        .fillMaxWidth()
                )
            } else {
                AsyncImage(
                    model = imagePath,
                    contentDescription = UiText.StringResource(
                        R.string.description_image_banner_perfil
                    ).asString(context = context),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(110.dp)
                        .fillMaxWidth(),

                    )
            }
        }
    }
}