package com.example.techhub.view

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
import com.example.techhub.composable.CnpjTextField
import com.example.techhub.composable.CpfTextField
import com.example.techhub.composable.ElevatedButtonTH
import com.example.techhub.composable.EmailTextField
import com.example.techhub.composable.NameTextField
import com.example.techhub.composable.PasswordTextField
import com.example.techhub.composable.TopBar
import com.example.techhub.ui.theme.GrayText
import com.example.techhub.ui.theme.PrimaryBlue
import com.example.techhub.utils.Screen

@Composable
fun CadastroFormView(navController: NavController, userType: String) {
    val name = remember { mutableStateOf("") }
    val userDocument = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = false,
                navController = navController,
                title = "Cadastro",
                route = Screen.TravaTelaCadastroScreen.route
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
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
                text = if (userType == "freelancer") "Quero ser um freelancer" else "Quero explorar talentos",
                style = TextStyle(
                    color = Color(PrimaryBlue.value),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            )

            Column(modifier = Modifier.padding(horizontal = 10.dp)) {

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = "Faça o cadastro para ter acesso aos nossos serviços!",
                    color = Color(GrayText.value),
                    fontWeight = FontWeight.Thin,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.padding(12.dp))

                NameTextField()

                Spacer(modifier = Modifier.padding(12.dp))

                if (userType == "freelancer") {
                    CpfTextField()
                } else if (userType == "empresa") {
                    CnpjTextField()
                }

                Spacer(modifier = Modifier.padding(12.dp))

                EmailTextField { email.value = it }

                Spacer(modifier = Modifier.padding(12.dp))

                PasswordTextField { password.value = it }

                Spacer(modifier = Modifier.padding(12.dp))

                ElevatedButtonTH(
                    onClick = {
                        // TODO - Add lógica p/ cadastro/validação de usuário
                    },
                    text = "Cadastrar",
                    backgroundColor = Color.White,
                    textColor = Color(PrimaryBlue.value),
                    width = (350),
                    height = (60)
                )
            }
        }
    }
}