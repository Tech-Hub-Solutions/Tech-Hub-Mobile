package com.example.techhub.presentation.perfil.composables.informacoesAdicionais

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.presentation.perfil.composables.SectionTitle
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun InformacoesAdicionaisSection(
    projetosFinalizados: Int,
    empresasInteressadas: Int,
    recomendacoes: Int
) {
    val informacoes = listOf(
        projetosFinalizados to InformacoesAdicionais.projetosFinalizados,
        empresasInteressadas to InformacoesAdicionais.empresasInteressadas,
        recomendacoes to InformacoesAdicionais.recomendacoes
    )

    SectionTitle(title = "Informações Adicionais", isCentered = true)

    Column(
        modifier = Modifier.fillMaxWidth(),
        Arrangement.spacedBy(12.dp)
    ) {
        for ((valor, descricao) in informacoes) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(valor.toString())
                    }

                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append(" $descricao")
                    }
                },
                fontSize = 14.sp,
                color = Color.Black,
            )
        }

        ElevatedButtonTH(
            onClick = {
                /* TODO - Requisição para recomandar */
            },
            text = "Recomendar",
            backgroundColor = Color.White,
            textColor = PrimaryBlue,
            width = 160,
            height = 40,
        )
    }
}

object InformacoesAdicionais {
    const val projetosFinalizados = "Projetos finalizados"
    const val empresasInteressadas = "Empresas interessadas"
    const val recomendacoes = "Recomendações"
}