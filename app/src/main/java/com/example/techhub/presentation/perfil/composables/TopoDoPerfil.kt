package com.example.techhub.presentation.perfil.composables

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techhub.common.composable.CircularProgressIndicatorTH
import com.example.techhub.common.enums.TipoArquivo
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.common.utils.uriToFile
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.presentation.editarUsuario.EditarUsuarioActivity
import com.example.techhub.presentation.perfil.PerfilViewModel
import com.example.techhub.presentation.perfil.composables.curriculo.MenuCurriculo
import com.example.techhub.presentation.perfil.composables.images.BannerImagePerfil
import com.example.techhub.presentation.perfil.composables.images.RoundedPerfilImage
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun TopoDoPerfil(
    userInfo: State<PerfilGeralDetalhadoData?>,
    isLoadingPerfil: State<Boolean?>,
    isLoadingWallpaper: State<Boolean?>,
    isLoadingCurriculo : State<Boolean?>,
    isOwnProfile: Boolean,
    isEmpresa: Boolean,
    viewModel: PerfilViewModel,
    context: Context,
    urlCurriculo: State<String?>
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

                        if (isFavorito.value) {
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
                    if (isLoadingCurriculo.value!!){
                            CircularProgressIndicatorTH()
                    } else {
                        val expanded = remember { mutableStateOf(false) }
                        IconButton(
                            onClick = {
                                expanded.value = !expanded.value
                            }
                        ) {
                            Icon(
                                // TODO - Lógica para quando for seu perfil ser UploadFile, senão InsertDriveFile ou FilePresent
                                imageVector = Icons.Filled.UploadFile,
                                contentDescription = "Currículo",
                                tint = PrimaryBlue,
                                modifier = Modifier.size(34.dp)
                            )
                        }
                        Box(){
                            MenuCurriculo(
                                expanded,
                                Modifier.align(Alignment.BottomCenter),
                                viewModel,
                                context,
                                urlCurriculo.value!!,
                                userInfo.value!!.nome!!
                            )
                        }
                    }


                    Spacer(modifier = Modifier.width(8.dp))
                }

                if (isOwnProfile) {
                    IconButton(onClick = {
                        val extras = Bundle()
                        extras.putSerializable("userInfo", userInfo.value!!)
                        startNewActivity(context, EditarUsuarioActivity::class.java, extras)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.EditNote,
                            contentDescription = "Editar Perfil",
                            tint = Color.Gray,
                            modifier = Modifier.size(34.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}