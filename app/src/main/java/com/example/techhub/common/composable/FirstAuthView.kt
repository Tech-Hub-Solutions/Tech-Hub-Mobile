package com.example.techhub.common.composable

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.utils.copyToClipBoard
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import com.example.techhub.domain.verifyUser
import com.example.techhub.presentation.cadastro.composables.QrCodeBase64AsyncImage
import com.example.techhub.presentation.login.LoginActivity
import com.example.techhub.presentation.perfil.PerfilActivity
import com.example.techhub.presentation.ui.theme.GrayButtonText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun FirstAuthView(
    usuarioSimpleVerifyData: UsuarioSimpleVerifyData,
    secretQrCodeUrl: String,
    toastErrorMessage: String,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    cancelarActivity: Class<*>
) {
    val context = LocalContext.current
    var authCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
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
        Spacer(modifier = Modifier.padding(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Habilitar autenticação de 2 fatores",
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

        QrCodeBase64AsyncImage(
            base64Image = secretQrCodeUrl,
            contentDescription = "QR Code para autenticação",
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Baixe o autenticador da Google (Google Authenticator)",
                color = Color.Red.copy(alpha = 0.6f),
                fontSize = 17.sp,
                fontWeight = FontWeight.Thin,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        val annotatedString = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Thin,
                    fontSize = 17.sp
                )
            ) {
                append("Impossibilitado de escanear o QR Code? Você pode usar a ")
            }

            withStyle(
                style = SpanStyle(
                    color = Color(PrimaryBlue.value),
                    fontWeight = FontWeight.Thin,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 17.sp,
                )
            ) {
                append("chave de configuração")
                addStringAnnotation(
                    tag = "Clickable",
                    annotation = "secretKey",
                    start = length - "chave de configuração".length,
                    end = length
                )
            }

            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Thin,
                    fontSize = 17.sp
                )
            ) {
                append(" para configurar manualmente o aplicativo de autenticação.")
            }
        }

        Text(
            text = annotatedString,
            modifier = Modifier.clickable(
                onClick = {
                    copyToClipBoard(
                        context = context,
                        copyText = usuarioSimpleVerifyData.secretKey,
                        toastText = "Código copiado"
                    )
                }
            ),
            textAlign = TextAlign.Justify
        )


        Spacer(modifier = Modifier.height(16.dp))

        androidx.compose.material3.OutlinedTextField(
            value = authCode,
            onValueChange = {
                authCode = it
            },
            label = {
                Text(
                    "Verifique o código do aplicativo",
                    Modifier.align(Alignment.CenterHorizontally)
                )
            },
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
                .align(Alignment.CenterHorizontally),
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
                    val extras = Bundle()
                    if (cancelarActivity == PerfilActivity::class.java) {
                        extras.putInt("id", 1)
                    }
                    startNewActivity(
                        context = context,
                        activity = cancelarActivity
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
                            email = usuarioSimpleVerifyData.email,
                            senha = usuarioSimpleVerifyData.senha,
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