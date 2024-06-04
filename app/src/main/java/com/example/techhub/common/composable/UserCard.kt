package com.example.techhub.common.composable

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.presentation.favoritos.FavoritosViewModel
import com.example.techhub.presentation.perfil.PerfilActivity
import com.example.techhub.presentation.ui.theme.GrayStar
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(
    userProfile: UsuarioFavoritoData,
    selectedUsers: MutableList<UsuarioFavoritoData>? = null,
    isAbleToFavorite: Boolean,
    modifier: Modifier = Modifier,
    isAbleToCompare: MutableState<Boolean>? = null,
    favoritesList: MutableList<UsuarioFavoritoData>? = null,
) {
    val context = LocalContext.current
    val isFavorito = remember { mutableStateOf(favoritesList?.contains(userProfile) ?: false) }
    val isSelected = remember { mutableStateOf(selectedUsers?.contains(userProfile) ?: false) }

    Log.d("FAVORITES LIST - STATUS 1", "isFavorito? ${userProfile.nome} - ${isFavorito.value}")

    val borderColor =
        if (isSelected.value && isAbleToCompare?.value == true) {
            Color(PrimaryBlue.value)
        } else {
            isSelected.value = false
            Color.Transparent
        }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
        modifier = Modifier
            .heightIn(204.dp, 300.dp)
            .border(
                2.dp,
                borderColor
            )
            .then(modifier),
        shape = RectangleShape,
        onClick = {
            if (isAbleToCompare?.value == true) {
                if (selectedUsers != null && selectedUsers.size < 2 ||
                    selectedUsers != null && selectedUsers.contains(userProfile)
                ) {
                    isSelected.value = !isSelected.value;

                    if (selectedUsers.contains(userProfile)) {
                        selectedUsers.remove(userProfile);
                    } else {
                        selectedUsers.add(userProfile);
                    }
                }
            } else {
                val extras = Bundle()
                extras.putInt("id", userProfile.id!!)
                startNewActivity(context, PerfilActivity::class.java, extras)
            }

        }
    ) {

        if (userProfile.urlFotoPerfil.isNullOrBlank()) {
            Icon(
                Icons.Filled.Person,
                contentDescription = UiText.StringResource(
                    R.string.btn_description_profile
                ).asString(context = context),
                tint = PrimaryBlue,
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()

                    .background(Color(0xFFE4E4E4))

            )
        } else {
            AsyncImage(
                model = userProfile.urlFotoPerfil,
                contentDescription = UiText.StringResource(
                    R.string.description_image_photo_freelancer
                ).asString(context = context),
                modifier = Modifier.fillMaxHeight(0.4f),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp)
        ) {
            Text(
                text = userProfile.nome.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Text(
                text = userProfile.descricao ?: UiText.StringResource(
                    R.string.desc_user_card
                ).asString(context = context),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
                ),
                modifier = Modifier.height(30.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.padding(2.dp))

            StarRatingBarFixed(
                maxStars = 5,
                rating = userProfile.qtdEstrela ?: 0.0,
                starSize = 5
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                var text = UiText.StringResource(
                    R.string.description_usercard_sem_preco
                        ).asString(context = context)
                var color = Color.Gray
                var fontSize = 14.sp

                if (userProfile.precoMedio?.isNaN() == false) {
                    text = "R$ ${"%.2f".format(userProfile.precoMedio)}"
                    color = Color.Black
                    fontSize = 14.sp

                }
                Text(
                    text = text,
                    style = TextStyle(
                        color = color,
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize
                    )
                )

                if (isAbleToFavorite) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = UiText.StringResource(
                            R.string.btn_description_favoritar_perfil
                        ).asString(context = context),
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                if (favoritesList!!.contains(userProfile)) {
                                    favoritesList.remove(userProfile)
                                    Log.d(
                                        "FAVORITES LIST - REMOVED",
                                        "CONTAINS: ${isFavorito.value}"
                                    )
                                } else {
                                    favoritesList!!.add(userProfile)
                                    Log.d("FAVORITES LIST - ADD", "CONTAINS: ${isFavorito.value}")
                                }

                                val viewModel = FavoritosViewModel()
                                viewModel.favoritarUsuario(userProfile.id)
                                isFavorito.value = !isFavorito.value

                                Log.d("FAVORITES LIST - STATUS", "${favoritesList.toList()}")
                            },
                        tint = if (isFavorito.value) Color.Red else Color(GrayStar.value),
                    )
                }
            }
        }
    }
}