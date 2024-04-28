package com.example.techhub.presentation.perfil.composables.avaliacao

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.composable.StarRatingBarFixed
import com.example.techhub.presentation.ui.theme.GrayText

@Composable
fun AvaliacaoSection(totalRating: Double) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Avaliações",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight(500),
            )
        }

        Row(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxSize(),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            // TODO - Tornar a quantidade de avaliações dinâmica
            Text(text = "58 avaliações recebidas", color = GrayText)

            StarRatingBarFixed(
                maxStars = 5,
                rating = totalRating,
                starSize = 10
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.spacedBy(8.dp)
        ) {
            AvaliacaoCountRow(
                titleNumber = "5",
                totalProgress = 0.7f,
                qtdEstrela = 50
            )
            AvaliacaoCountRow(
                titleNumber = "4",
                totalProgress = 0.5f,
                qtdEstrela = 5
            )
            AvaliacaoCountRow(
                titleNumber = "3",
                totalProgress = 0.2f,
                qtdEstrela = 2
            )
            AvaliacaoCountRow(
                titleNumber = "2",
                totalProgress = 0.0f,
                qtdEstrela = 0
            )
            AvaliacaoCountRow(
                titleNumber = "1",
                totalProgress = 0.1f,
                qtdEstrela = 1
            )
        }
    }
}