package com.example.techhub.presentation.perfil.composables

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.presentation.perfil.PerfilViewModel
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun TopoDoPerfil(
    userInfo: State<PerfilGeralDetalhadoData?>,
    isLoadingPerfil: State<Boolean?>,
    isLoadingWallpaper: State<Boolean?>,
    isOwnProfile: Boolean,
    isEmpresa: Boolean,
    viewModel: PerfilViewModel,
    context: Context
) {
    val isFavorito = remember {
        mutableStateOf(userInfo.value!!.isFavorito == true)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BannerImagePerfil(
            imagePath = userInfo.value!!.urlFotoWallpaper,
            isLoadingWallpaper.value!!
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, start = 24.dp),
            contentAlignment = Alignment.BottomStart,
        ) {
            RoundedPerfilImage(
                userInfo.value!!.urlFotoPerfil,
                isOwnProfile,
                perfilViewModel = viewModel,
                context,
                isLoadingPerfil.value!!
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 132.dp, end = 24.dp),
            contentAlignment = Alignment.BottomEnd,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (!isOwnProfile && !isEmpresa && CurrentUser.isEmpresa) {
                    IconButton(onClick = {
                        viewModel.favoritarPerfil(context, userInfo.value!!.idUsuario!!)
                        isFavorito.value = !isFavorito.value
                    }) {
                        var icon = Icons.Outlined.FavoriteBorder
                        var color = PrimaryBlue

                        if(isFavorito.value) {
                            icon = Icons.Filled.Favorite
                            color = Color(0xFFD32F2F)
                        }

                        Icon(
                            imageVector = icon,
                            contentDescription = "Favoritar",
                            tint = color,
                            modifier = Modifier.size(34.dp)
                        )
                    }
                }


                if (!isEmpresa && isOwnProfile) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            // TODO - Lógica para quando for seu perfil ser UploadFile, senão InsertDriveFile ou FilePresent
                            imageVector = Icons.Filled.UploadFile,
                            contentDescription = "Currículo",
                            tint = PrimaryBlue,
                            modifier = Modifier.size(34.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}