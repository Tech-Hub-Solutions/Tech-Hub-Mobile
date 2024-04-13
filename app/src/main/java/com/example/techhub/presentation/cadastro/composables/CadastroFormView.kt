package com.example.techhub.presentation.cadastro.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.techhub.common.Constants
import com.example.techhub.common.composable.CpfTextField
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.common.composable.EmailTextField
import com.example.techhub.common.composable.NameTextField
import com.example.techhub.common.composable.PasswordTextField
import com.example.techhub.common.composable.TopBar
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import com.example.techhub.common.Screen
import com.example.techhub.common.composable.CnpjTextField
import com.example.techhub.common.composable.Switch2FA
import com.example.techhub.common.enums.UsuarioFuncao
import com.example.techhub.common.utils.showToastError
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.usuario.UsuarioCriacaoData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CadastroFormView(navController: NavController, userType: String) {
    val name = remember { mutableStateOf("") }
    val userDocument = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isUsing2FA = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val toastErrorMessage = "Ops! Algo deu errado.\n Tente novamente."

    fun cadastrarUsuario() {
        val user = UsuarioCriacaoData(
            nome = name.value,
            email = email.value,
            senha = password.value,
            numeroCadastroPessoa = userDocument.value,
            funcao = if (userType == Constants.FREELANCER) UsuarioFuncao.FREELANCER else UsuarioFuncao.EMPRESA,
            isUsing2FA = isUsing2FA.value
        )

        val usuarioService = RetrofitService.getUsuarioService()

        usuarioService.cadastrarUsuario(user).enqueue(object : Callback<UsuarioTokenData> {
            override fun onResponse(
                call: Call<UsuarioTokenData>,
                response: Response<UsuarioTokenData>
            ) {
                if (response.isSuccessful) {
                    // TODO: Redirect to the next screen
                } else {
                    showToastError(context = context, message = toastErrorMessage)
                }
            }

            override fun onFailure(call: Call<UsuarioTokenData>, t: Throwable) {
                showToastError(context = context, message = toastErrorMessage)
            }
        })
    }

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = false,
                navController = navController,
                title = "Cadastro",
                route = Screen.TravaTelaCadastroView.route
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
                text = if (userType == Constants.FREELANCER) "Quero ser um freelancer" else "Quero explorar talentos",
                style = TextStyle(
                    color = Color(PrimaryBlue.value),
                    fontWeight = FontWeight.Medium,
                    fontSize = 32.sp,
                )
            )

            Column(modifier = Modifier.padding(horizontal = 10.dp)) {

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = "Faça o cadastro para ter acesso aos nossos serviços!",
                    color = Color(GrayText.value),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(12.dp))

                NameTextField { name.value = it }

                Spacer(modifier = Modifier.padding(12.dp))

                if (userType == Constants.FREELANCER) {
                    CpfTextField { userDocument.value = it }
                } else if (userType == Constants.EMPRESA) {
                    CnpjTextField { userDocument.value = it }
                }

                Spacer(modifier = Modifier.padding(12.dp))

                EmailTextField { email.value = it }

                Spacer(modifier = Modifier.padding(12.dp))

                PasswordTextField { password.value = it }

                Switch2FA { isUsing2FA.value = it }

                Spacer(modifier = Modifier.padding(12.dp))

                ElevatedButtonTH(
                    onClick = {
                        cadastrarUsuario()
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