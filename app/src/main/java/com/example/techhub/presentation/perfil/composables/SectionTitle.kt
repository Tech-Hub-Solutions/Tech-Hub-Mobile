package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionTitle(
    title: String,
    isCentered: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        if (isCentered) Arrangement.Center else Arrangement.Start
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight(500),
        )
    }

    Spacer(modifier = Modifier.height(8.dp))
}