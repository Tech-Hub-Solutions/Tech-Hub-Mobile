package com.example.techhub.presentation.login.components

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.composable.CenteredImageSection
import com.example.techhub.common.composable.TopBar
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.example.techhub.presentation.index.IndexActivity
import com.example.techhub.common.utils.showToastError
import retrofit2.Call
import com.example.techhub.R
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.common.composable.EmailTextField
import com.example.techhub.common.composable.PasswordTextField
import com.example.techhub.common.utils.showWelcomeToastWithName
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.presentation.cadastro.CadastroActivity
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginFormView(onLoginAuth: () -> Unit) {
    var email = remember { mutableStateOf("") }
    var senha = remember { mutableStateOf("") }
    val toastErrorMessage = "Ops! Algo deu errado.\n Tente novamente."
    val context = LocalContext.current

    fun loginUser() {
        val user = UsuarioLoginData(
            email = email.value,
            senha = senha.value
        )
        val usuarioService = RetrofitService.getUsuarioService()

        usuarioService.loginUser(user).enqueue(object : Callback<UsuarioTokenData> {
            override fun onResponse(
                call: Call<UsuarioTokenData>,
                response: Response<UsuarioTokenData>
            ) {
                val responseBody = response.body()

                if (response.isSuccessful) {
                    if (responseBody?.isUsing2FA!!) {
                        onLoginAuth()
                    } else {
                        showWelcomeToastWithName(
                            context = context,
                            fullName = response.body()?.nome!!,
                        )

                        startNewActivity(
                            context = context,
                            // TODO - Inserir redirecionamento para Activity de Perfil
                            // TODO - No parâmetro, passar o pefil e seu token p/ salvar no Data Store
                            IndexActivity::class.java
                        )
                    }
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
                willRedirectToActivity = true,
                activity = IndexActivity::class.java,
                context = context,
                title = "Login",
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
                    text = "Entre com sua conta para uma " +
                            "experiência melhor!",
                    color = Color(GrayText.value),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
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
                onClick = { loginUser() },
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

                TextButton(onClick = {
                    startNewActivity(
                        context = context,
                        CadastroActivity::class.java
                    )
                }) {
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
