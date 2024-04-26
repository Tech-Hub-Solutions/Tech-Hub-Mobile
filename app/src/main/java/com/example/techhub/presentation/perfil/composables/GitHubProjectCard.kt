package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.techhub.common.DevIconClassName
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun GitHubProjectCard() {
    val iconName = DevIconClassName.JavaScript
    val DEV_ICON_LANGUAGE_URL =
        "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/${iconName}/${iconName}-original.svg"

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .size(width = 180.dp, height = 190.dp)
            .background(color = Color.White)
            .padding(8.dp),
        content = {
            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                Text(text = "Projeto 1", color = PrimaryBlue, fontSize = 18.sp)

                Text(
                    text = "Um chat interativo feito em React e Sequelize",
                    color = Color.Black,
                    fontWeight = FontWeight.Thin,
                    fontSize = 14.sp,
                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(DEV_ICON_LANGUAGE_URL)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = "Angular Icon",
                    modifier = Modifier.size(45.dp)
                )
            }
        }
    )
}