package com.example.techhub.presentation.explorarTalentos.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.techhub.common.composable.BottomBar
import com.example.techhub.common.composable.TopBarTitle
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import com.example.techhub.presentation.explorarTalentos.ExplorarTalentosViewModel

@Composable
fun ExplorarTalentosView(viewModel: ExplorarTalentosViewModel = ExplorarTalentosViewModel()) {
    val ordem = remember { mutableStateOf("avaliacao,desc") }
    val (filtro, setFiltro) = remember { mutableStateOf(UsuarioFiltroData(tecnologiasIds = mutableListOf())) }
    val context = LocalContext.current

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    ModalDrawerSheet(
                        drawerContainerColor = Color(0xFFF8F8F8),
                    ) { FilterDrawerContent(setFiltro, viewModel, drawerState, scope) }
                }
            },
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Scaffold(
                    topBar = {
                        TopBarTitle(
                            title = "Explorar Talentos"
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
                        TalentosContent(
                            viewModel = viewModel,
                            filtro = filtro,
                            context = context,
                            ordem = ordem,
                            scope = scope,
                            drawerState = drawerState
                        )
                    }
                }
            }
        }
    }
}