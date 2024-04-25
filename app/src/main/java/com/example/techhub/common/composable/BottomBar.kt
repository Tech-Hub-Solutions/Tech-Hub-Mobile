package com.example.techhub.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Sms
import androidx.compose.material.icons.outlined.TravelExplore
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun BottomBar(isEmpresa: Boolean) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 5.dp,
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly,

            ) {
                IconButton(onClick = { /* TODO - redirecionar para a página de busca talentos */ }) {
                    Icon(
                        Icons.Outlined.TravelExplore,
                        contentDescription = "@string/btn_description_search_talents",
                        tint = Color(PrimaryBlue.value),
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp)
                    )
                }
                if (isEmpresa) {
                    IconButton(onClick = { /* TODO - redirecionar para os favoritos */ }) {
                        Icon(
                            Icons.Filled.FavoriteBorder,
                            contentDescription = "@string/btn_description_favorites",
                            tint = Color(PrimaryBlue.value),
                            modifier = Modifier
                                .width(28.dp)
                                .height(28.dp)
                        )
                    }
                }
                IconButton(onClick = { /* TODO - inserir a foto de perfil da pessoa + abrir o pop de editar perfil */ }) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "@string/btn_description_profile",
                        tint = Color(PrimaryBlue.value),
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp)
                    )
                }
            }
        },
        containerColor = Color.White,
    )
}
