package com.example.techhub.common.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.techhub.presentation.ui.theme.PrimaryBlue


@Composable
fun CardTravaTelaCadastro(
    imagePath: Int,
    contentDescription: String,
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    var backgroundColor = if (isSelected) Color(0xFFADD8E6) else Color.White

    Box(
        modifier = Modifier
            .height(148.dp)
            .width(148.dp)
            .background(backgroundColor, shape = MaterialTheme.shapes.extraSmall)
            .clickable { onClick() },
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
        ) {
            Image(
                painter = painterResource(id = imagePath),
                contentDescription = contentDescription,
                modifier = Modifier
                    .height(116.dp)
                    .width(106.dp)
            )
            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color(PrimaryBlue.value)
            )
        }
    }
}