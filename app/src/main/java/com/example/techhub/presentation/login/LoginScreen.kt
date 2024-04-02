package com.example.techhub.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.techhub.common.composable.CenteredImageSection
import com.example.techhub.R
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.common.composable.EmailTextField
import com.example.techhub.common.composable.PasswordTextField
import com.example.techhub.common.composable.TopBar
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import com.example.techhub.common.Screen
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var email = remember { mutableStateOf("") }
    var senha = remember { mutableStateOf("") }

    val usuario = UsuarioLoginData(
        email = email.value,
        senha = senha.value
    )

    /*
    * fun loginUser() {
        val user = UsuarioLoginData(
            email = email.value,
            senha = senha.value
        )
        val call = RetrofitService.loginUser().loginUser(user)

        call.enqueue(object : Callback<UsuarioTokenData> {
            override fun onResponse(
                call: Call<UsuarioTokenData>,
                response: Response<UsuarioTokenData>
            ) {
                val responseBody = response.body()

                if (responseBody != null) {
                    val token = response.body()!!.token

                    // TODO - encontrar forma de usar DataStore p/ storeData(token)
                    response.body()?.token?.let {
                        navController.navigate(Screen.LoginAuthScreen.route)
                    } ?: showError(navController)

                } else {
                    Toast.makeText(
                        navController.context,
                        "Ops! Algo deu errado." +
                                "\n Tente novamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // TODO - Retirar print
                    println(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<UsuarioTokenData>, t: Throwable) {
                showError(navController)
                // TODO - Retirar print
                println("Erro ao logar: ${t.message}")
            }
        })
    }
    * */


    Scaffold(
        topBar = {
            TopBar(navController = navController, title = "Login", route = Screen.IndexScreen.route)
        },
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
            Spacer(modifier = Modifier.padding(12.dp))

            CenteredImageSection(
                imagePath = R.mipmap.login_image,
                contentDescription = "@string/description_image_login",
                width = 252,
                height = 300,
            )

            Spacer(modifier = Modifier.padding(24.dp))

            Row {
                Text(
                    text = "Entre com sua conta para uma \n" +
                            "experiência melhor!",
                    color = Color(GrayText.value),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.padding(12.dp))

            Column(
                modifier = Modifier
                    .width(350.dp)
            ) {
                EmailTextField { email.value = it }

                PasswordTextField { senha.value = it }
            }

            Spacer(modifier = Modifier.padding(12.dp))

            ElevatedButtonTH(
                onClick = { viewModel.viewModelScope.launch {
                    viewModel.login(usuario = usuario)
                } },
                text = "Entrar",
                backgroundColor = Color(PrimaryBlue.value),
                width = (350),
                height = (60),
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Não tem uma conta?",
                    color = Color.Black,
                    fontWeight = FontWeight.Thin,
                    fontSize = 14.sp
                )

                TextButton(onClick = { navController.navigate(Screen.CadastroScreen.route) }) {
                    Text(
                        text = "Cadastre-se.",
                        color = Color(PrimaryBlue.value),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Thin,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}

fun showError(navController: NavController) {
    Toast.makeText(
        navController.context,
        "Ops! Algo deu errado.\n Tente novamente.",
        Toast.LENGTH_SHORT
    ).show()
}

