package com.example.techhub.presentation.cadastro.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.techhub.common.utils.base64Images.base64ToBitmap

@Composable
fun QrCodeBase64AsyncImage(base64Image: String, contentDescription: String) {
    val base64ImageString = base64Image.split(",")
    val decodeBitmap = base64ImageString[1].base64ToBitmap()
    val imgBit = decodeBitmap.asImageBitmap()

    Image(
        bitmap = imgBit,
        contentDescription = contentDescription,
        modifier = Modifier.size(200.dp)
    )
}