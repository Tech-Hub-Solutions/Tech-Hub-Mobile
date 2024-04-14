package com.example.techhub.presentation.comparar.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
    model: AccordionModel.Row) {
    Row(
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            model.title,
            Modifier.weight(1f),
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            color = Color.Gray
        )
        Surface(color = Color.Green, shape = RoundedCornerShape(8.dp), shadowElevation = 2.dp) {
            Text(
                text = model.description,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }
    }
}