package com.example.techhub.presentation.comparar.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CardUsuarioComparacao() {
    Surface(
        modifier = Modifier
            .width(180.dp)
            .heightIn(230.dp, 280.dp),
        shadowElevation = 10.dp
    ) {
        Column(
            Modifier
                .background(color = Color.White)
        ) {
            AsyncImage(
                model = "https://www.w3schools.com/w3images/avatar2.png",
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.FillWidth
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "Bruno", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Desenvolvedor frontend focado em programação ",
                    fontSize = 14.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left
                )
            }
        }
    }
}

@Preview
@Composable
fun CardUsuarioComparacaoPreview() {
    CardUsuarioComparacao()
}