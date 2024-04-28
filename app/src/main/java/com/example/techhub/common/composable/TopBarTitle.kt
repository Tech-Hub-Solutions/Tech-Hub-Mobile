package com.example.techhub.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun TopBarTitle(title: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 24.sp,
            fontWeight = FontWeight(500),
            color = PrimaryBlue,
            modifier = Modifier.background(Color.Transparent),
        )
    }
}