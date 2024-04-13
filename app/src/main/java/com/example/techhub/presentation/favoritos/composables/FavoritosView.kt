package com.example.techhub.presentation.favoritos.composables

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.techhub.common.composable.BottomBar
import com.example.techhub.common.composable.TopBar
import com.example.techhub.common.utils.showToastError
import com.example.techhub.composable.OrderDropDownMenu
import com.example.techhub.composable.UserCard
import com.example.techhub.presentation.favoritos.FavoritosViewModel
import com.example.techhub.presentation.index.IndexActivity

@Composable
fun FavoritosView() {
    var expanded by remember { mutableStateOf(false) }
    var ordem = remember { mutableStateOf("avaliacao,desc") }
    var erroApi = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(true) }
    val context = LocalContext.current


    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = true,
                activity = IndexActivity::class.java,
                context = context,
                title = "Favoritos",
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
                    bottom = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

                getFavoriteUsers(
                    viewModel = FavoritosViewModel(),
                    context = context,
                    ordem = ordem
                )

        }
    }
}

@Composable
fun getFavoriteUsers(
    viewModel: FavoritosViewModel = FavoritosViewModel(),
     context: Context,
    ordem: MutableState<String>
) {

    val favoritos = viewModel.favoritos.observeAsState().value!!
    val erroApi = viewModel.erroApi.observeAsState().value!!

    if (erroApi.isNotEmpty()) {
        showToastError(context = context, message = erroApi)
    }

    LaunchedEffect(ordem.value) {
        viewModel.getFavoriteUsers(0, 10, "",ordem.value)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(text = "${favoritos.size} profissionais encontrados")

        Spacer(modifier = Modifier.padding(horizontal = 25.dp))

        OrderDropDownMenu(ordem)
    }

    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    LazyColumn(
        modifier = Modifier
            .verticalScroll(
                rememberScrollState()
            )
            .height(920.dp)
    ) {
        val subLists = favoritos.chunked(2)

        itemsIndexed(subLists) { index, subLista ->
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (item in subLista) {
                    UserCard(item, favoritos)
                }
            }
        }
    }
}