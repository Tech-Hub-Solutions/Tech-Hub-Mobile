package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.composable.BottomBar
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.presentation.perfil.PerfilViewModel
import com.example.techhub.presentation.perfil.composables.avaliacao.AvaliacaoSection
import com.example.techhub.presentation.perfil.composables.comentario.ComentariosSection
import com.example.techhub.presentation.perfil.composables.informacoesAdicionais.InformacoesAdicionaisSection
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PerfilView(id: Int, viewModel: PerfilViewModel = PerfilViewModel()) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val isEmpresa = CurrentUser.isEmpresa
    val isOwnProfile = id == CurrentUser.userProfile?.id
    val context = LocalContext.current
    val userInfo = viewModel.usuario.observeAsState().value!!
    val isLoading = viewModel.isLoading.observeAsState().value!!
    val isLoadingPerfil = viewModel.isLoadingPerfil.observeAsState().value!!
    val isLoadingWallpaper = viewModel.isLoadingWallpaper.observeAsState().value!!

    LaunchedEffect(Unit) {
        viewModel.getInfosUsuario(context = context, userId = id)
    }

    Scaffold(
        bottomBar = {
            BottomBar()
        },
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
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
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(bottom = 60.dp)
            ) {
                // Banner e imagem de perfil
                // todo - tirar text
                Text(text = "Atual id: $id. IsOwnProfile: $isOwnProfile")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    BannerImagePerfil(imagePath = userInfo.urlFotoWallpaper)

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 80.dp, start = 24.dp),
                        contentAlignment = Alignment.BottomStart,
                    ) {
                        RoundedPerfilImage(userInfo.urlFotoPerfil, isOwnProfile, perfilViewModel = viewModel, context, isLoadingPerfil)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 132.dp, end = 24.dp),
                        contentAlignment = Alignment.BottomEnd,
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // TODO - Add lógica de que se não for o perfil da pessoa, mostrar o icone de favoritos
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Currículo",
                                    tint = PrimaryBlue,
                                    modifier = Modifier.size(34.dp)
                                )
                            }


                            if (!isEmpresa) {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        // TODO - Lógica para quando for seu perfil ser UploadFile, senão InsertDriveFile ou FilePresent
                                        imageVector = Icons.Filled.UploadFile,
                                        contentDescription = "Currículo",
                                        tint = PrimaryBlue,
                                        modifier = Modifier.size(34.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                    }
                }

                // Nome e Infos
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${userInfo.nome}",
                            fontSize = 24.sp,
                            color = Color.Black,
                            fontWeight = FontWeight(500),
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            // TODO - inserir lógica para renderizar bandeira do país
                            text = "🍕",
                        )

                        Text(
                            text = "${userInfo.pais}",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight(200),
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Row {
                            IconButton(onClick = { /* TODO - redirecionar para o Linkedin*/ }) {
                                Image(
                                    painter = painterResource(id = com.example.techhub.R.drawable.linkedin_icon),
                                    contentDescription = "botão para redirecionar ao LinkedIn",
                                    Modifier.size(30.dp)
                                )
                            }


                            if (!isEmpresa) {
                                IconButton(onClick = { /* TODO - redirecionar para o GitHub*/ }) {
                                    Image(
                                        painter = painterResource(id = com.example.techhub.R.drawable.github_icon),
                                        contentDescription = "botão para redirecionar ao GitHub",
                                        Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row {
                        Text(
                            text = "Empresa de inovação digital",
                            fontSize = 18.sp,
                            color = Color(PrimaryBlue.value),
                            fontWeight = FontWeight(400),
                        )
                    }
                }

                Divider(
                    color = Color.LightGray.copy(alpha = 0.4f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 20.dp)
                )

                // Column das informações pós header/banner
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    /* TODO - inserir lógica para coletar informações de perfil */
                    if (!isEmpresa) {
                        TextContainer(
                            title = "Experiência",
                            description = "${userInfo.experiencia}"
                        )
                    }

                    TextContainer(
                        title = if (isEmpresa) "Sobre nós" else "Sobre mim",
                        description = "${userInfo.sobreMim}"
                    )

                    if (isEmpresa) {
                        TextContainer(
                            title = "Quem procuramos",
                            description = "${userInfo.descricao}"
                        )
                    }

                    if (!isEmpresa) {
                        TagsSection(title = "Soft Skills")

                        Divider(
                            color = Color.LightGray.copy(alpha = 0.4f),
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )

                        TagsSection(title = "Hard Skills")
                    } else {
                        TagsSection(title = "Valores")
                    }

                    Divider(
                        color = Color.LightGray.copy(alpha = 0.4f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    if (!isEmpresa) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            SectionTitle(title = "Projetos desenvolvidos", isCentered = false)

                            FlowRow(
                                modifier = Modifier.horizontalScroll(rememberScrollState())
                            ) {
                                GitHubProjectCard()
                                GitHubProjectCard()
                                GitHubProjectCard()
                            }
                        }

                        Divider(
                            color = Color.LightGray.copy(alpha = 0.4f),
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }

                    // Seção de Avaliações
                    AvaliacaoSection(totalRating = 4.0)

                    Divider(
                        color = Color.LightGray.copy(alpha = 0.4f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    // Seção de Informações Adicionais
                    InformacoesAdicionaisSection(
                        projetosFinalizados = 10,
                        empresasInteressadas = 5,
                        recomendacoes = 3
                    )
                }

                // Seção de Comentários
                ComentariosSection()
            }

            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd,
            ) {
                FloatingActionButtonScroll(
                    isScrolled = scrollState.value > 0,
                    scrollState = scrollState,
                    scope = scope
                )
            }
        }
    }
}