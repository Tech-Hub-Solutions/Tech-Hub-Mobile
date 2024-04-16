package com.example.techhub.presentation.comparar.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AccordionRow(
    model: AccordionModel.Row
) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Surface(color = Color.Green, shape = RoundedCornerShape(8.dp), shadowElevation = 2.dp) {
            Icon(
                imageVector = if (model.user1) Icons.Default.Verified else Icons.Default.Close,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
        Text(
            model.tecnologia,
            Modifier.weight(1f),
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            color = Color.Gray
        )
        Surface(color = Color.Green, shape = RoundedCornerShape(8.dp), shadowElevation = 2.dp) {
            Icon(
                imageVector = if (model.user2) Icons.Default.Verified else Icons.Default.Close,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}