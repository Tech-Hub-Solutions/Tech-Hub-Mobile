package com.example.techhub.presentation.perfil.composables.comentario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.techhub.common.composable.CustomizedElevatedButton
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.presentation.perfil.composables.SectionTitle
import com.example.techhub.presentation.ui.theme.GrayLoadButton
import com.example.techhub.presentation.ui.theme.GrayStar
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun ComentariosSection() {
    val desc =
        "O desenvolvedor é uma verdadeira peça-chave em nossa equipe. Sempre traz soluções inovadoras e entrega resultados excepcionais. Sua habilidade de comunicação também enriquece nossa colaboração."

    Column(modifier = Modifier.background(GrayLoadButton)) {
        Divider(
            color = Color.LightGray.copy(alpha = 0.4f),
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(modifier = Modifier.padding(bottom = 16.dp, start = 24.dp, end = 24.dp)) {
            SectionTitle(title = "Comentários", isCentered = false)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp, start = 24.dp, end = 24.dp),
            Arrangement.spacedBy(16.dp)
        ) {
            ComentarioCard(description = desc)
            ComentarioCard(description = desc)
            ComentarioCard(description = desc)
            ComentarioCard(description = desc)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomizedElevatedButton(
                    onClick = {
                        /* TODO - Add função para aparecer mais comentários */
                    },
                    horizontalPadding = 16,
                    verticalPadding = 8,
                    defaultElevation = 0,
                    pressedElevation = 0,
                    containerColor = Color(GrayStar.value),
                    contentColor = Color(0xFF505050),
                    shape = RoundedCornerShape(50),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterHorizontally
                    ),
                    text = "Ver mais",
                    fontSize = 16,
                    fontWeight = FontWeight.Medium,
                    contentDescription = "Botão para carregar mais talentos"
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    ComentarioForm()

    Spacer(modifier = Modifier.height(16.dp))

    ElevatedButtonTH(
        onClick = { /* TODO - Publicar comentário */ },
        text = "Comentar",
        backgroundColor = PrimaryBlue,
        width = 160,
        height = 40,
    )
}