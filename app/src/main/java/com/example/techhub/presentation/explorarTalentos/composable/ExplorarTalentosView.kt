package com.example.techhub.presentation.explorarTalentos.composable

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.composable.BottomBar
import com.example.techhub.common.composable.CustomizedElevatedButton
import com.example.techhub.common.composable.TopBarTitle
import com.example.techhub.common.utils.showToastError
import com.example.techhub.composable.OrderDropDownMenu
import com.example.techhub.common.composable.UserCard
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import com.example.techhub.presentation.explorarTalentos.ExplorarTalentosViewModel
import com.example.techhub.presentation.ui.theme.GrayLoadButton
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.GrayTinyButton
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ExplorarTalentosView(viewModel: ExplorarTalentosViewModel = ExplorarTalentosViewModel()) {
    val ordem = remember { mutableStateOf("avaliacao,desc") }
    val (filtro, setFiltro) = remember { mutableStateOf(UsuarioFiltroData(tecnologiasIds = mutableListOf())) }
    val erroApi = remember { mutableStateOf("") }
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
                        GetTalentos(
                            viewModel = viewModel,
                            filtro = filtro,
                            context = context,
                            ordem = ordem,
                            scope = scope,
                            drawerState
                        )

                    }
                }
            }
        }
    }
}


@Composable
fun GetTalentos(
    viewModel: ExplorarTalentosViewModel,
    filtro: UsuarioFiltroData,
    context: Context,
    ordem: MutableState<String>,
    scope: CoroutineScope,
    drawerState: DrawerState
) {

    val talentos = viewModel.talentos.observeAsState().value!!
    val erroApi = viewModel.erroApi.observeAsState().value!!
    val isLoading = viewModel.isLoading.observeAsState().value!!
    val isLastPage = viewModel.isLastPage.observeAsState().value!!
    val totalElements = viewModel.totalElements.observeAsState().value!!
    val page = remember { mutableStateOf(0) }

    if (erroApi.isNotEmpty()) {
        showToastError(context = context, message = erroApi)
    }

    LaunchedEffect(Unit) {
        viewModel.getFlags()
    }

    LaunchedEffect(ordem.value) {
        page.value = 0
        viewModel.getTalentos(0, 10, ordem.value, UsuarioFiltroData())
    }

    LaunchedEffect(filtro) {
        page.value = 0
        viewModel.getTalentos(0, 10, ordem.value, filtro)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "${totalElements} profissionais encontrados",
            modifier = Modifier.fillMaxWidth(0.4f),
            color = GrayText
        )

        IconButton(
            onClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            },
            modifier = Modifier
                .size(86.dp, 25.dp)
                .background(Color(GrayTinyButton.value))
                .padding(0.dp),
        ) {
            Row(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val color = if (
                    filtro.tecnologiasIds?.isEmpty() == true
                    && filtro.area.isNullOrEmpty()
                    && filtro.nome.isNullOrEmpty()
                    && filtro.precoMax == null
                    && filtro.precoMin == null
                ) {
                    GrayText
                } else {
                    PrimaryBlue
                }
                Text(
                    text = "Filtrar",
                    fontSize = 14.sp,
                    color = color
                )
                Image(
                    painter = painterResource(id = R.mipmap.filter),
                    contentDescription = "Filter",
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        OrderDropDownMenu(ordem)
    }

    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    if (erroApi.isNotEmpty()) {
        Text(erroApi, color = Color.Red, fontSize = 16.sp)
    }

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .heightIn(max = 1000.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val subLists = talentos.chunked(2)
                var count = 0
                itemsIndexed(subLists) { index, subLista ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start)
                    ) {
                        subLista.forEachIndexed { index, item ->
                            count++;
                            UserCard(
                                item,
                                talentos,
                                isComparing = false,
                                modifier = Modifier
                                    .weight(1f, false)
                            )

                            if (index == subLista.size - 1 && subLista.size % 2 != 0) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }

                    if (index == subLists.size - 1 && !isLastPage) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CustomizedElevatedButton(
                                onClick = {
                                    viewModel.getTalentos(++page.value, 10, ordem.value, filtro)
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
}
