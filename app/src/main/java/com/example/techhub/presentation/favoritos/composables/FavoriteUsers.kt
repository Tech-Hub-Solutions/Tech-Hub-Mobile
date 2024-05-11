package com.example.techhub.presentation.favoritos.composables

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.techhub.common.composable.CircularProgressIndicatorTH
import com.example.techhub.common.composable.CompareSwitch
import com.example.techhub.common.composable.CustomizedElevatedButton
import com.example.techhub.common.composable.UserCard
import com.example.techhub.composable.OrderDropDownMenu
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.presentation.favoritos.FavoritosViewModel
import com.example.techhub.presentation.ui.theme.GrayLoadButton
import com.example.techhub.presentation.ui.theme.GrayText

@Composable
fun FavoriteUsers(
    context: Context,
    viewModel: FavoritosViewModel = FavoritosViewModel(),
    ordem: MutableState<String>,
    selectedUsers: SnapshotStateList<UsuarioFavoritoData>,
    isAbleToCompare: MutableState<Boolean>,
) {
    val favoritos = viewModel.favoritos.observeAsState().value!!
    val totalElements = viewModel.totalElements.observeAsState()
    val erroApi = viewModel.erroApi.observeAsState().value!!
    val isLoading = viewModel.isLoading.observeAsState().value!!
    val isLastPage = viewModel.isLastPage.observeAsState().value!!

    val page = remember { mutableStateOf(0) }

    LaunchedEffect(ordem.value) {
        page.value = 0
        viewModel.getFavoriteUsers(page.value, 30, ordem.value, context)
    }

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicatorTH()
        }
    } else {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(text = "${totalElements.value} profissionais encontrados", color = GrayText)

            OrderDropDownMenu(ordem)
        }

        CompareSwitch {
            isAbleToCompare.value = it
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        if (favoritos.isEmpty()) {
            Text(text = "Nenhum favorito selecionado")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val subLists = favoritos.chunked(2)

            itemsIndexed(subLists) { index, subLista ->
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start)
                ) {
                    subLista.forEachIndexed { index, item ->
                        UserCard(
                            item,
                            selectedUsers,
                            true,
                            modifier = Modifier.weight(1f, false),
                            isAbleToCompare
                        )

                        if (index == subLista.size - 1 && subLista.size % 2 != 0) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            item {
                if (!isLastPage && favoritos.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CustomizedElevatedButton(
                            onClick = {
                                viewModel.getFavoriteUsers(++page.value, 30, ordem.value, context)
                            },
                            horizontalPadding = 16,
                            verticalPadding = 8,
                            defaultElevation = 0,
                            pressedElevation = 0,
                            containerColor = Color(GrayLoadButton.value),
                            contentColor = Color(0xFF505050),
                            shape = RoundedCornerShape(50),
                            horizontalArrangement = Arrangement.spacedBy(
                                8.dp,
                                Alignment.CenterHorizontally
                            ),
                            text = "Carregar mais talentos",
                            fontSize = 16,
                            fontWeight = FontWeight.Medium,
                            contentDescription = "Botão para carregar mais talentos"
                        )
                    }
                }
            }
        }
    }
}