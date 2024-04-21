package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RoundedPerfilImage(imagePath: Int) {
    Image(
        painter = painterResource(id = imagePath),
        contentDescription = "Rounded Perfil Image",
        modifier = Modifier
            .size(115.dp)
            .clip(CircleShape)
            .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
    )
}