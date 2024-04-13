package com.example.techhub.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.presentation.favoritos.FavoritosViewModel
import com.example.techhub.presentation.ui.theme.GrayStar

@Composable
fun UserCard(
    userProfile: UsuarioFavoritoData, userList: SnapshotStateList<UsuarioFavoritoData>,
) {
    val isFavorito = remember { mutableStateOf(true) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 200.dp, height = 204.dp),
        shape = RectangleShape
    ) {
        AsyncImage(
            model = userProfile.urlFotoPerfil,
            contentDescription = "Foto do freelancer",
            modifier = Modifier.size(148.dp, 98.dp),
            alignment = Alignment.TopCenter
        )

        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(6.dp)
        ) {
            Text(
                text = userProfile.nome.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Text(
                text = userProfile.descricao.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
                ),
                modifier = Modifier.height(30.dp)
            )

            Spacer(modifier = Modifier.padding(2.dp))

            StarRatingBar(
                maxStars = 5,
                rating = userProfile.qtdEstrela ?: 0.0
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Row {
                Text(
                    text = "R$ ${"%.2f".format(userProfile.precoMedio)}",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )

                Spacer(modifier = Modifier.padding(horizontal = 24.dp))

                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Botão para favoritar",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            val viewModel = FavoritosViewModel()

                            viewModel.favoritarUsuario(userProfile.id)
                            isFavorito.value = !isFavorito.value
                        },
                    tint = if (isFavorito.value) Color.Red else Color(GrayStar.value),
                )
            }
        }

    }
}


@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Double,
) {
    val density = LocalDensity.current.density
    val starSize = (5 * density).dp
    val starSpacing = (0.5 * density).dp

    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Default.Star
            val iconTintColor = if (isSelected) Color(0xFFFFC700) else Color(GrayStar.value)
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier
                    .width(starSize)
                    .height(starSize)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}


