package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun BannerImagePerfil(imagePath: String?) {
    Row {
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
                contentDescription = "imagem de capa do banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(110.dp)
                    .fillMaxWidth(),

            )
        }
    }
}