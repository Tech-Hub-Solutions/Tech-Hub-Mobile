package com.example.techhub.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BannerImagePerfil(imagePath: Int) {
    /* TODO - inserir lógica para fazer requisição de imagem e passar o imagePath como parâmetro */
    Row {
        Image(
            painter = painterResource(id = imagePath),
            contentDescription = "imagem de capa do banner",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(110.dp)
                .fillMaxWidth()
        )
    }
}