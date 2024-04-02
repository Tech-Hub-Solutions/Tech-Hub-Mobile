package com.example.techhub.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.techhub.common.composable.CnpjTextField
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.common.composable.EmailTextField
import com.example.techhub.common.composable.NameTextField
import com.example.techhub.common.composable.PasswordTextField
import com.example.techhub.common.composable.TopBar
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import com.example.techhub.common.Screen

@Composable
fun CadastroEmpresaView(navController: NavController) {
    val nomeEmpresa = remember { mutableStateOf("") }
    val cnpjEmpresa = remember { mutableStateOf("") }
    val emailEmpresa = remember { mutableStateOf("") }
    val senhaEmpresa = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                title = "Cadastro",
                route = Screen.CadastroScreen.route
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 24.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(20.dp))

            Text(
                text = "Quero explorar talentos",
                style = TextStyle(
                    color = Color(PrimaryBlue.value),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = "Faça o cadastro para ter acesso aos nossos serviços!",
                color = Color(GrayText.value),
                fontWeight = FontWeight.Thin,
                fontSize = 14.sp
            )
            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                Spacer(modifier = Modifier.padding(12.dp))

                NameTextField()

                Spacer(modifier = Modifier.padding(12.dp))

                CnpjTextField()

                Spacer(modifier = Modifier.padding(12.dp))

                EmailTextField { emailEmpresa.value = it }

                Spacer(modifier = Modifier.padding(12.dp))

                PasswordTextField { senhaEmpresa.value = it }

                Spacer(modifier = Modifier.padding(12.dp))
            }


            ElevatedButtonTH(
                onClick = {

                },
                text = "Cadastrar",
                backgroundColor = Color.White,
                textColor = Color(PrimaryBlue.value),
                width = (360),
                height = (60)
            )

        }
    }
}
