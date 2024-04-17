package com.example.techhub.presentation.explorarTalentos.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.techhub.common.composable.BottomBar
import com.example.techhub.common.composable.SearchBar
import com.example.techhub.common.composable.TopBar
import com.example.techhub.composable.OrderDropDownMenu
import com.example.techhub.presentation.favoritos.FavoritosViewModel
import com.example.techhub.presentation.favoritos.composables.createBottomSheet
import com.example.techhub.presentation.favoritos.composables.getFavoriteUsers
import com.example.techhub.presentation.index.IndexActivity

@Composable
fun ExplorarTalentosView(){
    val expanded by remember { mutableStateOf(false) }
    val ordem = remember{ mutableStateOf("avaliacao,desc") }
    val erroApi = remember { mutableStateOf("") }
    val loading = remember{ mutableStateOf(true) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = true,
                activity = IndexActivity::class.java,
                context = context,
                title = "Procurar Talentos",
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
           SearchBar()
        }
    }
}
