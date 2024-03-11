package com.example.techhub

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun EditableForm(height: Double, width: Double, backcroundColor: Color,
                 imagePath: Int, contentDescription: String, text: String, textColor : Color) {
    Box(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp)
            .background(backcroundColor, shape = MaterialTheme.shapes.extraSmall),
        contentAlignment = Alignment.TopCenter

    ) {
        Column(
        ) {
            Image(
                painter = painterResource(id = imagePath),
                contentDescription = contentDescription,
                modifier = Modifier.height(116.dp).width(106.dp)
            )
            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

        }
    }
}
