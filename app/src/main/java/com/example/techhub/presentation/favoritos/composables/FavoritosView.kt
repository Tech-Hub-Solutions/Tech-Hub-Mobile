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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.composable.BottomBar
import com.example.techhub.common.composable.TopBar
import com.example.techhub.common.utils.showToastError
import com.example.techhub.composable.OrderDropDownMenu
import com.example.techhub.composable.UserCard
import com.example.techhub.domain.model.flag.FlagData
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.presentation.comparar.composables.CompararTalentosView
import com.example.techhub.presentation.favoritos.FavoritosViewModel
import com.example.techhub.presentation.index.IndexActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritosView() {
    var scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val selectedUsers = remember {
        mutableStateListOf(
            UsuarioFavoritoData(
                id = 2,
                nome = "Leonardo",
                descricao = "Desenvolvedor Front-End",
                qtdEstrela = 4.0,
                precoMedio = 600.0,
                urlFotoPerfil = "/api/arquivos/usuario/2/imagem?tipoArquivo=PERFIL",
                flags = listOf(
                    FlagData(
                        id = 3,
                        nome = "React",
                        area = "Front-end",
                        categoria = "hard-skill"
                    ),
                    FlagData(
                        id = 9,
                        nome = "Django",
                        area = "Back-end",
                        categoria = "hard-skill"
                    ),
                    FlagData(
                        id = 11,
                        nome = "ASP.NET",
                        area = "Back-end",
                        categoria = "hard-skill"
                    ),
                    FlagData(
                        id = 14,
                        nome = "Swift (iOS)",
                        area = "Mobile",
                        categoria = "hard-skill"
                    ),
                    FlagData(
                        id = 19,
                        nome = "MySQL",
                        area = "Banco de Dados",
                        categoria = "hard-skill"
                    ),
                    FlagData(
                        id = 24,
                        nome = "Oracle Database",
                        area = "Banco de Dados",
                        categoria = "hard-skill"
                    ),
                    FlagData(
                        id = 67,
                        nome = "Colaboração",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 68,
                        nome = "Comunicação",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 69,
                        nome = "Comprometimento",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 70,
                        nome = "Confiança",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 71,
                        nome = "Criatividade",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 72,
                        nome = "Diversidade e Inclusão",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 73,
                        nome = "Ética de Trabalho",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 74,
                        nome = "Empatia",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 75,
                        nome = "Entusiasmo",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 76,
                        nome = "Equilíbrio Trabalho-Vida",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    )
                )
            ),
            UsuarioFavoritoData(
                id = 3,
                nome = "Ana",
                descricao = "Cientista de Dados",
                qtdEstrela = 5.0,
                precoMedio = 600.0,
                urlFotoPerfil = "/api/arquivos/usuario/3/imagem?tipoArquivo=PERFIL",
                flags = listOf(
                    FlagData(
                        id = 1,
                        nome = "HTML/CSS",
                        area = "Front-end",
                        categoria = "hard-skill"
                    ),
                    FlagData(
                        id = 4,
                        nome = "Angular",
                        area = "Front-end",
                        categoria = "hard-skill"
                    ),
                    FlagData(
                        id = 8,
                        nome = "Express.js",
                        area = "Back-end",
                        categoria = "hard-skill"
                    ),
                    FlagData(
                        id = 13,
                        nome = "Kotlin (Android)",
                        area = "Mobile",
                        categoria = "hard-skill"
                    ),
                    FlagData(
                        id = 17,
                        nome = "Objective-C (iOS)",
                        area = "Mobile",
                        categoria = "hard-skill"
                    ),
                    FlagData(id = 25, nome = "Jest", area = "Testes", categoria = "hard-skill"),
                    FlagData(
                        id = 67,
                        nome = "Colaboração",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 68,
                        nome = "Comunicação",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 69,
                        nome = "Comprometimento",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 70,
                        nome = "Confiança",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 71,
                        nome = "Criatividade",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 72,
                        nome = "Diversidade e Inclusão",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 73,
                        nome = "Ética de Trabalho",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 74,
                        nome = "Empatia",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 75,
                        nome = "Entusiasmo",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    ),
                    FlagData(
                        id = 76,
                        nome = "Equilíbrio Trabalho-Vida",
                        area = "Soft-skills",
                        categoria = "soft-skill"
                    )
                )
            )
        )
    }
    teste()
    BottomSheetScaffold(
        modifier = Modifier
            .fillMaxHeight(.98f)
            .verticalScroll(rememberScrollState()),
        scaffoldState = scaffoldState,
        sheetContent = {
            CompararTalentosView(scope, selectedUsers)
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

        //
    }
}

@Composable
fun teste() {
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
        viewModel.getFavoriteUsers(0, 10, "", ordem.value)
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
                    UserCard(item, favoritos)
                }
            }
        }
    }
}

fun spacedBy(space: Dp, alignment: Alignment.Horizontal): Arrangement.Horizontal {
    return Arrangement.spacedBy(space, alignment)
}