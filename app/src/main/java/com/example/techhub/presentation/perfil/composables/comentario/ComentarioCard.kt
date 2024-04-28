package com.example.techhub.presentation.perfil.composables.comentario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.techhub.common.composable.StarRatingBarFixed

@Composable
fun ComentarioCard(description: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White),
        content = {
            // TODO - Trazer nome e foto do usuário e rating do comentário
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                Arrangement.SpaceBetween,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://angular.io/assets/images/logos/angular/angular.svg")
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = "Angular Icon",
                    modifier = Modifier.size(45.dp)
                )

                Column(
                    modifier = Modifier,
                    Arrangement.spacedBy(6.dp)
                ) {
                    TextButton(
                        onClick = { /* TODO - Redirecionar para o perfil do usuário */ },
                        shape = RoundedCornerShape(0.dp),
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        content = {
                            Column {
                                Text(
                                    text = "TechSolutions",
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(0.dp),
                                )
                                Text(text = "Brasil", color = Color.Black, fontSize = 14.sp)
                            }
                        }
                    )

                    StarRatingBarFixed(rating = 3.0, starSize = 8)

                    Text(
                        text = description,
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight(200),
                        textAlign = TextAlign.Justify,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }
    )
}