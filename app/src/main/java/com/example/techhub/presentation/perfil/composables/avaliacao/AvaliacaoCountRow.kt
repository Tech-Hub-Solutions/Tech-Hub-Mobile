package com.example.techhub.presentation.perfil.composables.avaliacao

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AvaliacaoCountRow(
    titleNumber: String,
    qtdEstrela: String,
    totalProgress: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$titleNumber " + if (titleNumber == "1") "estrela " else "estrelas",
            color = Color.Black
        )

        AvaliacaoProgressIndicator(totalProgress = totalProgress)

        Text(text = "($qtdEstrela)", color = Color.Black)
    }
}