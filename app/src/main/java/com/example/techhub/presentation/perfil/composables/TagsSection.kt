package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSection(title: String) {
    Column {
        SectionTitle(title = title, isCentered = false)

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