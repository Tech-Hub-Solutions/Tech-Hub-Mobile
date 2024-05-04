package com.example.techhub.presentation.perfil.composables.projetosDesenvolvidos

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.techhub.common.devIconMap
import com.example.techhub.common.utils.openLink
import com.example.techhub.domain.model.github.Repositorio
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitHubProjectCard(
    repositorio: Repositorio
) {
    val contexto = LocalContext.current
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = {
            if (repositorio.url.isNullOrBlank()) return@ElevatedCard
            openLink(contexto, repositorio.url)
        },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .size(width = 180.dp, height = 190.dp)
            .background(color = Color.White)
            .padding(8.dp),
        content = {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                Arrangement.SpaceBetween,
            ) {
                Text(
                    text = repositorio.name ?: "Projeto sem nome",
                    color = PrimaryBlue,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = repositorio.description ?: "Projeto sem descrição",
                    color = Color.Black,
                    fontWeight = FontWeight.Thin,
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )

                Row(Modifier.horizontalScroll(rememberScrollState())) {
                    repositorio.languages.forEach {
                        if (it.isBlank()) return@forEach
                        val icon = devIconMap[it]

                        if (icon.isNullOrEmpty()) {
                            Text(text = it)
                        } else {
                            val languageIcon =
                                "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/${icon}/${icon}-original.svg"
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(languageIcon)
                                    .decoderFactory(SvgDecoder.Factory())
                                    .build(),
                                contentDescription = "$it Icon",
                                modifier = Modifier.size(45.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}