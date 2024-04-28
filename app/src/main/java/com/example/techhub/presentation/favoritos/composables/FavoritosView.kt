package com.example.techhub.presentation.favoritos.composables

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.composable.BottomBar
import com.example.techhub.common.composable.CompareSwitch
import com.example.techhub.common.composable.CustomizedElevatedButton
import com.example.techhub.common.composable.TopBarTitle
import com.example.techhub.common.utils.showToastError
import com.example.techhub.composable.OrderDropDownMenu
import com.example.techhub.common.composable.UserCard
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.presentation.comparar.composables.CompararTalentosView
import com.example.techhub.presentation.favoritos.FavoritosViewModel
import com.example.techhub.presentation.ui.theme.GrayLoadButton
import com.example.techhub.presentation.ui.theme.GrayText
import kotlinx.coroutines.launch


@Composable
fun FavoritosView() {
    val selectedUsers = remember { SnapshotStateList<UsuarioFavoritoData>() }
    var ordem = remember { mutableStateOf("avaliacao,desc") }
    val isAbleToCompare = remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var erroApi = remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarTitle(
                title = "Favoritos"
            )
        },
        bottomBar = { BottomBar(isEmpresa = true) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            getFavoriteUsers(
                viewModel = FavoritosViewModel(context),
                context = context,
                ordem = ordem,
                selectedUsers = selectedUsers,
                isAbleToCompare = isAbleToCompare,
            )
        }
    }
    createBottomSheet(selectedUsers = selectedUsers, isAbleToCompare)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createBottomSheet(
    selectedUsers: SnapshotStateList<UsuarioFavoritoData>,
    isAbleToCompare: MutableState<Boolean>
) {
    var scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    if (isAbleToCompare.value == false && selectedUsers.isNotEmpty()) {
        selectedUsers.clear()
    } else {
        if (selectedUsers.size == 2) {
            BottomSheetScaffold(
                modifier = Modifier
                    .fillMaxHeight(.98f)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 56.dp),
                scaffoldState = scaffoldState,
                sheetContainerColor = Color.White,
                sheetContent = {
                    CompararTalentosView(selectedUsers)
                },
                sheetPeekHeight = 70.dp,
                sheetDragHandle = {
                    TextButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(70.dp)
                            .background(color = Color(0xFF0F9EEA))
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Comparar Selecionados",
                                color = Color.White,
                                fontSize = 24.sp
                            )
                            Icon(
                                imageVector = Icons.Filled.CompareArrows,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                },
                sheetShape = MaterialTheme.shapes.extraSmall
            ) {

            }
        }
    }

}

@Composable
fun getFavoriteUsers(
    context: Context,
    viewModel: FavoritosViewModel = FavoritosViewModel(context),
    ordem: MutableState<String>,
    selectedUsers: SnapshotStateList<UsuarioFavoritoData>,
    isAbleToCompare: MutableState<Boolean>,
) {
    val favoritos = viewModel.favoritos.observeAsState().value!!
    val erroApi = viewModel.erroApi.observeAsState().value!!
    val isLastPage = viewModel.isLastPage.observeAsState().value!!

    val page = remember { mutableStateOf(0) }

    if (erroApi.isNotEmpty()) {
        showToastError(context = context, message = erroApi)
    }

    LaunchedEffect(ordem.value) {
        page.value = 0
        viewModel.getFavoriteUsers(page.value, 10, "", ordem.value)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(text = "${favoritos.size} profissionais encontrados", color = GrayText)

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
        modifier = Modifier
            .verticalScroll(
                rememberScrollState()
            )
            .height(920.dp),
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
                for (item in subLista) {
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
    }
    if (!isLastPage && favoritos.isNotEmpty()) {
        CustomizedElevatedButton(
            onClick = {
                page.value += 1
                viewModel.getFavoriteUsers(page.value, 3, "", ordem.value)
            },
            horizontalPadding = 16,
            verticalPadding = 8,
            defaultElevation = 0,
            pressedElevation = 0,
            containerColor = Color(GrayLoadButton.value),
            contentColor = Color(0xFF505050),
            shape = RoundedCornerShape(50),
            horizontalArrangement = spacedBy(8.dp, Alignment.CenterHorizontally),
            text = "Carregar mais talentos",
            fontSize = 16,
            fontWeight = FontWeight.Medium,
            contentDescription = "Botão para carregar mais talentos"
        )
    }
}

fun spacedBy(space: Dp, alignment: Alignment.Horizontal): Arrangement.Horizontal {
    return Arrangement.spacedBy(space, alignment)
}