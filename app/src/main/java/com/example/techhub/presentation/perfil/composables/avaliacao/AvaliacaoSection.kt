package com.example.techhub.presentation.perfil.composables.avaliacao

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.techhub.common.composable.StarRatingBarFixed
import com.example.techhub.presentation.perfil.PerfilViewModel
import com.example.techhub.presentation.perfil.composables.SectionTitle
import com.example.techhub.presentation.ui.theme.GrayText
import kotlin.math.roundToInt

@Composable
fun AvaliacaoSection(
    usuarioId: Int,
    viewModel: PerfilViewModel,
    context: Context
) {
    val avaliacoes = viewModel.avaliacoesDoUsuario.observeAsState()
    var totalAvaliacoes = avaliacoes.value!!.sumOf { it.quantidade?.toInt() ?: 0 }
    totalAvaliacoes = if (totalAvaliacoes == 0) 1 else totalAvaliacoes
    val totalAvaliacoesPorEstrela =
        avaliacoes.value!!.sumOf { (it.quantidade?.toInt() ?: 0) * (it.qtdEstrela ?: 1) }
    val totalRating = (totalAvaliacoesPorEstrela.toDouble() / totalAvaliacoes).roundToInt()

    LaunchedEffect(Unit) {
        viewModel.getAvaliacoesDoUsuario(context, usuarioId)
    }


    SectionTitle(title = "Avaliações", isCentered = true)
    Column(
        modifier = Modifier.fillMaxWidth(),
        Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxSize(),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            // TODO - Tornar a quantidade de avaliações dinâmica
            Text(text = "$totalAvaliacoes avaliações recebidas", color = GrayText)

            StarRatingBarFixed(
                maxStars = 5,
                rating = totalRating.toDouble(),
                starSize = 7
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.spacedBy(8.dp)
        ) {
            for (i in 5 downTo 1) {
                AvaliacaoCountRow(
                    titleNumber = i.toString(),
                    qtdEstrela = avaliacoes.value!!.find { it.qtdEstrela == i }?.quantidade?.toInt()
                        ?: 0,
                    totalProgress = (avaliacoes.value!!.find { it.qtdEstrela == i }?.quantidade?.toFloat()
                        ?: 0f) / totalAvaliacoes
                )
            }
//            AvaliacaoCountRow(
//                titleNumber = "5",
//                totalProgress = 0.7f,
//                qtdEstrela = 50
//            )
//            AvaliacaoCountRow(
//                titleNumber = "4",
//                totalProgress = 0.5f,
//                qtdEstrela = 5
//            )
//            AvaliacaoCountRow(
//                titleNumber = "3",
//                totalProgress = 0.2f,
//                qtdEstrela = 2
//            )
//            AvaliacaoCountRow(
//                titleNumber = "2",
//                totalProgress = 0.0f,
//                qtdEstrela = 0
//            )
//            AvaliacaoCountRow(
//                titleNumber = "1",
//                totalProgress = 0.1f,
//                qtdEstrela = 1
//            )
        }
    }
}