package com.example.techhub.presentation.perfil.composables.avaliacao

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AvaliacaoCountRow(
    titleNumber: String,
    qtdEstrela: Int,
    totalProgress: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            text = "$titleNumber " + if (titleNumber == "1") "estrela " else "estrelas",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier
                .weight(1f)
        )

        AvaliacaoProgressIndicator(totalProgress = totalProgress, modifier = Modifier.weight(5f))

        Text(
            text = "($qtdEstrela)",
            color = Color.Black,
            modifier = Modifier
                .weight(0.6f),
            textAlign = TextAlign.End
        )
    }
}