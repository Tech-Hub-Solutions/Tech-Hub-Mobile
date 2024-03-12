package com.example.techhub.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 4.dp)
    ) {

        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight(500),
        )

        Spacer(modifier = Modifier.height(8.dp))

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