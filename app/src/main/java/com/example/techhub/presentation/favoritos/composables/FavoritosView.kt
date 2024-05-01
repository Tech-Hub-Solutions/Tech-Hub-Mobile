package com.example.techhub.presentation.favoritos.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.techhub.common.composable.BottomBar
import com.example.techhub.common.composable.TopBarTitle
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.presentation.favoritos.FavoritosViewModel

@Composable
fun FavoritosView() {
    val selectedUsers = remember { SnapshotStateList<UsuarioFavoritoData>() }
    var ordem = remember { mutableStateOf("avaliacao,desc") }
    val isAbleToCompare = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarTitle(
                title = "Favoritos"
            )
        },
        bottomBar = { BottomBar() }
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

            FavoriteUsers(
                viewModel = FavoritosViewModel(),
                context = context,
                ordem = ordem,
                selectedUsers = selectedUsers,
                isAbleToCompare = isAbleToCompare,
            )
        }
    }

    BottomSheetCompararTalentos(selectedUsers = selectedUsers, isAbleToCompare)
}