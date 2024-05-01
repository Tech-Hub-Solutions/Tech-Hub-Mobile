package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun DetalhesUsuario(
    userInfo: State<PerfilGeralDetalhadoData?>,
    isEmpresa: Boolean
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${userInfo.value!!.nome}",
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight(500),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                // TODO - inserir lógica para renderizar bandeira do país
                text = "🍕",
            )

            Text(
                text = "${userInfo.value!!.pais}",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight(200),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Row {
                IconButton(onClick = { /* TODO - redirecionar para o Linkedin*/ }) {
                    Image(
                        painter = painterResource(id = com.example.techhub.R.drawable.linkedin_icon),
                        contentDescription = "botão para redirecionar ao LinkedIn",
                        Modifier.size(30.dp)
                    )
                }


                if (!isEmpresa) {
                    IconButton(onClick = { /* TODO - redirecionar para o GitHub*/ }) {
                        Image(
                            painter = painterResource(id = com.example.techhub.R.drawable.github_icon),
                            contentDescription = "botão para redirecionar ao GitHub",
                            Modifier.size(30.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row {
            Text(
                text = userInfo.value!!.descricao ?: "sem descrição",
                fontSize = 18.sp,
                color = Color(PrimaryBlue.value),
                fontWeight = FontWeight(400),
            )
        }
    }
}