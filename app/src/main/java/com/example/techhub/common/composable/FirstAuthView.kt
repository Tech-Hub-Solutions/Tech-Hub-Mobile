package com.example.techhub.common.composable

import android.os.Bundle
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.MutableLiveData
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.copyToClipBoard
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import com.example.techhub.common.utils.verifyUser
import com.example.techhub.presentation.cadastro.composables.QrCodeBase64AsyncImage
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
    val isLoading = MutableLiveData(false)

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
                text = UiText.StringResource(
                    R.string.text_authenticator
                ).asString(context = context),
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
            contentDescription = UiText.StringResource(
                R.string.description_image_qr_code
            ).asString(context = context),
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = UiText.StringResource(
                    R.string.text_scan_qr_code
                ).asString(context = context),
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
                append(
                    UiText.StringResource(
                        R.string.append_text_authenticator_qrcode
                    ).asString(context = context)
                )
            }

            withStyle(
                style = SpanStyle(
                    color = Color(PrimaryBlue.value),
                    fontWeight = FontWeight.Thin,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 17.sp,
                )
            ) {
                append(
                    UiText.StringResource(
                        R.string.append_text_authenticator_secret_key
                    ).asString(context = context)
                )
                addStringAnnotation(
                    tag = "Clickable",
                    annotation = "secretKey",
                    start = length - UiText.StringResource(
                        R.string.append_text_authenticator_secret_key
                    ).asString(context = context).length,
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
                append(
                    UiText.StringResource(
                        R.string.append_text_authenticator_config_manually
                    ).asString(context = context)
                )
            }
        }

        Text(
            text = annotatedString,
            modifier = Modifier.clickable(
                onClick = {
                    copyToClipBoard(
                        context = context,
                        copyText = usuarioSimpleVerifyData.secretKey,
                        toastText = UiText.StringResource(
                            R.string.toast_text_copy_secret_key
                        ).asString(context = context)
                    )
                }
            ),
            textAlign = TextAlign.Justify
        )


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = authCode,
            onValueChange = {
                authCode = it
            },
            label = {
                Text(
                    UiText.StringResource(
                        R.string.label_auth_code
                    ).asString(context = context),
                    Modifier.align(Alignment.CenterHorizontally)
                )
            },
            placeholder = {
                Text(
                    UiText.StringResource(
                        R.string.placeholder_auth_code
                    ).asString(context = context)
                )
            },
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
                    text = UiText.StringResource(
                        R.string.btn_text_cancelar
                    ).asString(context = context),
                    color = Color(GrayButtonText.value),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(300)
                )
            }

            ElevatedButton(
                onClick = {
                    verifyUser(
                        userData = UsuarioVerifyData(
                            email = usuarioSimpleVerifyData.email,
                            senha = usuarioSimpleVerifyData.senha,
                            code = authCode
                        ),
                        context = context,
                        toastErrorMessage = toastErrorMessage,
                        isLoading = isLoading
                    )
                },
                modifier = Modifier
                    .padding(10.dp)
                    .width(130.dp)
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(PrimaryBlue.value),
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(10.dp),
            ) {
                if (isLoading.observeAsState().value!!) {
                    CircularProgressIndicatorTH(size = 30.0, color = Color.White)
                } else {
                    Text(
                        text = UiText.StringResource(
                            R.string.btn_text_continuar
                        ).asString(context = context),
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
                            toastErrorMessage = toastErrorMessage,
                        )
                    },
                    text = UiText.StringResource(
                        R.string.btn_text_continuar
                    ).asString(context = context),
                    backgroundColor = Color(PrimaryBlue.value),
                    textColor = Color.White,
                    width = 130,
                    height = 52,
                )

            }
        }
    }
}
