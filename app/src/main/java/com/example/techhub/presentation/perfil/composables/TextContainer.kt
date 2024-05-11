package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextContainer(title: String, description: String) {
    Column {
        SectionTitle(title = title, isCentered = false)

        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight(200),
            textAlign = TextAlign.Justify,
        )

        Divider(
            color = Color.LightGray.copy(alpha = 0.4f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}