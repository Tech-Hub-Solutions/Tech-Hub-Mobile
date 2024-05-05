package com.example.techhub.presentation.perfil.composables

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.countriesEmoji
import com.example.techhub.common.utils.openLink
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.presentation.ui.theme.PrimaryBlue


@Composable
fun DetalhesUsuario(
    context: Context,
    userInfo: State<PerfilGeralDetalhadoData?>,
    isEmpresa: Boolean
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = "${userInfo.value!!.nome}",
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(500),
                )

                Text(
                    text = countriesEmoji.countries.get(userInfo.value!!.pais) ?: "🌍",
                    fontSize = 24.sp,
                )
            }


            Row {
                val linkLinkedin = userInfo.value!!.linkLinkedin
                val linkGithub = userInfo.value!!.linkGithub
                IconButton(onClick = {
                    if (!linkLinkedin.isNullOrEmpty()) {
                        openLink(context, linkLinkedin)
                    }
                }) {
                    Image(
                        painter = painterResource(id = com.example.techhub.R.drawable.linkedin_icon),
                        contentDescription = "botão para redirecionar ao LinkedIn",
                        Modifier.size(30.dp),
                        colorFilter = if (linkLinkedin.isNullOrEmpty()) ColorFilter.colorMatrix(
                            ColorMatrix().apply { setToSaturation(0f) }
                        ) else null
                    )
                }

                IconButton(onClick = {
                    if (!linkGithub.isNullOrEmpty()) {
                        openLink(context, linkGithub)
                    }
                }) {
                    Image(
                        painter = painterResource(id = com.example.techhub.R.drawable.github_icon),
                        contentDescription = "botão para redirecionar ao GitHub",
                        Modifier.size(30.dp),
                        colorFilter = if (linkGithub.isNullOrEmpty()) ColorFilter.tint(
                            Color.Gray
                        ) else null
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row {
            Text(
                text = userInfo.value!!.descricao ?: "Sem descrição",
                fontSize = 18.sp,
                color = Color(PrimaryBlue.value),
                fontWeight = FontWeight(400),
            )
        }
    }
}