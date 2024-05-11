package com.example.techhub.presentation.login.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.composable.CenteredImageSection
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.common.composable.TopBar
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import com.example.techhub.common.utils.verifyUser
import com.example.techhub.presentation.login.LoginActivity
import com.example.techhub.presentation.ui.theme.GrayButtonText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun LoginAuthView(
    usuarioVerifyData: UsuarioLoginData,
) {
    var authCode by remember { mutableStateOf("") }
    val context = LocalContext.current
    val toastErrorMessage = "Erro ao verificar usuário"

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = true,
                title = "Login",
                activity = LoginActivity::class.java,
                context = context
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 24.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(12.dp))

            CenteredImageSection(
                imagePath = R.mipmap.login_auth_lock_image,
                contentDescription = "@string/description_image_login_auth",
                width = 252,
                height = 300,
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Insira o código de autorização",
                    color = Color(PrimaryBlue.value),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    lineHeight = 38.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.padding(16.dp))

            OutlinedTextField(
                value = authCode,
                onValueChange = {
                    authCode = it
                },
                label = { Text("Código de verificação") },
                placeholder = { Text("Digite o código") },
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                ),
                colors = TextFieldDefaults.colors(
                    cursorColor = Color(PrimaryBlue.value),
                    errorCursorColor = Color(PrimaryBlue.value),
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    errorSupportingTextColor = Color.Red.copy(alpha = 0.6f),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Go,
                ),
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {
                        startNewActivity(
                            context = context,
                            activity = LoginActivity::class.java
                        )
                    },
                    modifier = Modifier
                        .width(130.dp)
                        .height(52.dp)
                ) {
                    Text(
                        text = "Cancelar",
                        color = Color(GrayButtonText.value),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(300)
                    )
                }

                ElevatedButtonTH(
                    onClick = {
                        verifyUser(
                            userData = UsuarioVerifyData(
                                email = usuarioVerifyData.email,
                                senha = usuarioVerifyData.senha,
                                code = authCode
                            ),
                            context = context,
                            toastErrorMessage = toastErrorMessage
                        )
                    },
                    text = "Continuar",
                    backgroundColor = Color(PrimaryBlue.value),
                    textColor = Color.White,
                    width = 130,
                    height = 52,
                )
            }
        }
    }
}

