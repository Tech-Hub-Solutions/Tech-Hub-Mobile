package com.example.techhub.presentation.perfil.composables.informacoesAdicionais

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.objects.InformacoesAdicionaisPerfil
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.presentation.perfil.PerfilViewModel
import com.example.techhub.presentation.perfil.composables.SectionTitle
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun InformacoesAdicionaisSection(
    isOwnProfile: Boolean,
    usuarioId: Int,
    empresasInteressadas: Int,
    recomendacoes: Int,
    isRecomendado: Boolean,
    viewModel: PerfilViewModel,
    context: Context
) {
    val isRecomendado = remember {
        mutableStateOf(isRecomendado)
    }
    val qtdRecomendacoes = remember {
        mutableIntStateOf(recomendacoes)
    }

    val informacoes = mutableListOf(
        qtdRecomendacoes.intValue to InformacoesAdicionaisPerfil.recomendacoes
    )

    if (!isOwnProfile) {
        informacoes.add(empresasInteressadas to InformacoesAdicionaisPerfil.empresasInteressadas)
    }

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

        if (!isOwnProfile) {
            val color = if (isRecomendado.value) Color.White else PrimaryBlue
            val background = if (isRecomendado.value) PrimaryBlue else Color.White
            val text = if (isRecomendado.value) "Recomendado" else "Recomendar"
            ElevatedButtonTH(
                onClick = {
                    viewModel.recomendarUsuario(context, usuarioId)
                    isRecomendado.value = !isRecomendado.value
                    qtdRecomendacoes.intValue = if (isRecomendado.value) {
                        qtdRecomendacoes.intValue + 1
                    } else {
                        qtdRecomendacoes.intValue - 1
                    }
                },
                text = text,
                backgroundColor = background,
                textColor = color,
                width = 160,
                height = 40,
            )
        } else {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
        }
    }
}