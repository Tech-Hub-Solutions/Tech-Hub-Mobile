package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSection(title: String) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight(500),
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier,
            Arrangement.spacedBy(8.dp),
            Arrangement.Top,
        ) {
            Tag(title = "Responsabilidade", backgroundColor = Color(0xFFCDEAF9))
            Tag(title = "Proatividade", backgroundColor = Color(0xFFCDEAF9))
            Tag(title = "Comunicação", backgroundColor = Color(0xFFCDEAF9))
            Tag(title = "Inovação", backgroundColor = Color(0xFFCDEAF9))
            Tag(title = "Paciência", backgroundColor = Color(0xFFCDEAF9))
            Tag(title = "Resiliência", backgroundColor = Color(0xFFCDEAF9))
        }
    }
}