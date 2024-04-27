package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.composable.BottomBar
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PerfilView() {
    /* TODO - inserir lógica para transferir estado de se é empresa ou freelancer */
    val isEmpresa = false

    Scaffold(
        bottomBar = {
            BottomBar(isEmpresa = isEmpresa)
        },
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            // Banner e imagem de perfil
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BannerImagePerfil(imagePath = com.example.techhub.R.mipmap.banner_perfil_image)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 80.dp, start = 24.dp),
                    contentAlignment = Alignment.BottomStart,
                ) {
                    RoundedPerfilImage(imagePath = com.example.techhub.R.mipmap.rounded_perfil_image)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 132.dp, end = 24.dp),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    Row {
                        if (!isEmpresa) {
                            ElevatedButton(
                                onClick = { },
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(45.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(PrimaryBlue.value),
                                    contentColor = Color.White,
                                ),
                                border = BorderStroke(
                                    1.dp, Color(PrimaryBlue.value)
                                ),
                                shape = RoundedCornerShape(10.dp),
                            ) {
                                Text(
                                    text = "Currículo",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight(300)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))
                        }

                        ElevatedButton(
                            onClick = { },
                            modifier = Modifier
                                .width(130.dp)
                                .height(45.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(PrimaryBlue.value),
                                contentColor = Color.White,
                            ),
                            border = BorderStroke(
                                1.dp, Color(PrimaryBlue.value)
                            ),
                            shape = RoundedCornerShape(10.dp),
                        ) {
                            Text(
                                text = "Editar perfil",
                                fontSize = 15.sp,
                                fontWeight = FontWeight(300)
                            )
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
                        text = "Tech Inovation",
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
                        text = "Itália",
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
                        description = "Na minha jornada, liderei projetos desafiadores, " +
                                "desde aplicativos móveis para grandes marcas até sistemas" +
                                "de gerenciamento robustos, sempre buscando a excelência " +
                                "técnica e funcional."
                    )
                }

                TextContainer(
                    title = if (isEmpresa) "Sobre nós" else "Sobre mim",
                    description = "Sou um entusiasta da tecnologia dedicado, apaixonado " +
                            "por resolver problemas complexos de maneira criativa. " +
                            "Minha busca incessante por aprendizado impulsiona meu " +
                            "constante crescimento na área de desenvolvimento."
                )

                if (isEmpresa) {
                    TextContainer(
                        title = "Quem procuramos",
                        description = "Valorizamos a diversidade de perspectivas e " +
                                "experiências, acreditando que é isso que impulsiona a " +
                                "nossa criatividade e sucesso. Se você é um pensador ágil, " +
                                "ansioso para enfrentar desafios complexos e comprometido " +
                                "em alcançar a excelência, você é a pessoa que estamos procurando."
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
                        Text(
                            text = "Projetos desenvolvidos",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight(500),
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        FlowRow(
                            modifier = Modifier.horizontalScroll(rememberScrollState())
                        ) {
                            GitHubProjectCard()
                            GitHubProjectCard()
                            GitHubProjectCard()
                        }
                    }
                }

                Divider(
                    color = Color.LightGray.copy(alpha = 0.4f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                // Seção de avaliações
                AvaliacaoSection()
            }
        }

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd,
        ) {
            FloatingActionButtonScroll(isScrolled = true)
        }
    }
}