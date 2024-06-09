package com.example.techhub.presentation.cadastro.composables

import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.techhub.R
import com.example.techhub.common.composable.CircularProgressIndicatorTH
import com.example.techhub.common.composable.CpfTextField
import com.example.techhub.common.composable.EmailTextField
import com.example.techhub.common.composable.NameTextField
import com.example.techhub.common.composable.PasswordTextField
import com.example.techhub.common.composable.TopBar
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import com.example.techhub.common.enums.Screen
import com.example.techhub.common.composable.CnpjTextField
import com.example.techhub.common.composable.CompanyNameTextField
import com.example.techhub.common.composable.Switch2FA
import com.example.techhub.common.enums.UsuarioFuncao
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.base64Images.encodeBase64
import com.example.techhub.common.utils.redirectToPerfilUsuario
import com.example.techhub.common.utils.showToastError
import com.example.techhub.data.prefdatastore.DataStoreManager
import com.example.techhub.domain.model.datastore.DataStoreData
import com.example.techhub.domain.service.RetrofitService
import com.example.techhub.domain.model.updateCurrentUser
import com.example.techhub.domain.model.usuario.UsuarioCriacaoData
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import kotlinx.coroutines.flow.first
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CadastroFormView(
    navController: NavController,
    userType: String,
    onAuthSuccess: (UsuarioSimpleVerifyData) -> Unit,
) {
    val isLoading = MutableLiveData(false)
    var name by remember { mutableStateOf("") }
    var userDocument by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isUsing2FA by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val toastErrorMessage = "Ops! Algo deu errado.\n Tente novamente."
    val focusRequester = remember { FocusRequester() }
    val dataStoreManager = DataStoreManager(context = context)
    var dataStoreData: DataStoreData? = null

    LaunchedEffect(key1 = dataStoreManager) {
        dataStoreData = dataStoreManager.getFromDataStore().first()
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    fun cadastrarUsuario() {
        isLoading.postValue(true)

        val user = UsuarioCriacaoData(
            nome = name,
            email = email,
            senha = password,
            numeroCadastroPessoa = userDocument,
            funcao = if (userType == UsuarioFuncao.FREELANCER.toString()) UsuarioFuncao.FREELANCER else UsuarioFuncao.EMPRESA,
            isUsing2FA = isUsing2FA
        )

        val authService = RetrofitService.getAuthService()

        authService.cadastrarUsuario(user).enqueue(object : Callback<UsuarioTokenData> {
            override fun onResponse(
                call: Call<UsuarioTokenData>,
                response: Response<UsuarioTokenData>
            ) {

                if (response.isSuccessful) {
                    dataStoreData?.let { updateCurrentUser(it) }

                    if (isUsing2FA) {
                        val secretQrCodeUrl = response.body()?.secretQrCodeUrl.toString()
                        val secretKey = response.body()?.secret.toString()
                        val encodeUrl = encodeBase64(secretQrCodeUrl)

                        val usuarioSimpleVerifyData = UsuarioSimpleVerifyData(
                            email = email,
                            senha = password,
                            encodedUrl = encodeUrl,
                            secretKey = secretKey
                        )

                        onAuthSuccess(usuarioSimpleVerifyData)
                    } else {
                        updateCurrentUser(
                            context = context,
                            usuarioTokenData = response.body()!!,
                            email = user.email!!
                        )

                        val extras = Bundle()
                        extras.putInt("id", response.body()?.id!!)

                        redirectToPerfilUsuario(
                            context = context,
                            fullName = response.body()?.nome!!,
                            extras = extras
                        )
                    }
                } else {
                    showToastError(context = context, message = toastErrorMessage)
                    isLoading.postValue(false)
                }
            }

            override fun onFailure(call: Call<UsuarioTokenData>, t: Throwable) {
                showToastError(context = context, message = toastErrorMessage)
                isLoading.postValue(false)
            }
        })
    }

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = false,
                navController = navController,
                title = UiText.StringResource(
                    R.string.title_cadastro
                ).asString(context = context),
                route = Screen.TravaTelaCadastroView.route,
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
            Spacer(modifier = Modifier.padding(20.dp))

            Text(
                text = if (userType == UsuarioFuncao.FREELANCER.toString()) {
                    UiText.StringResource(
                        R.string.text_entry_cadastro_freelancer
                    ).asString(context = context)

                } else {
                    UiText.StringResource(
                        R.string.text_entry_cadastro_empresa
                    ).asString(context = context)

                },
                style = TextStyle(
                    color = Color(PrimaryBlue.value),
                    fontWeight = FontWeight.Medium,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                )
            )

            Column(modifier = Modifier.padding(horizontal = 10.dp)) {

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = UiText.StringResource(
                        R.string.sub_text_cadastro_formview
                    ).asString(context = context),
                    color = Color(GrayText.value),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(12.dp))

                if (userType == UsuarioFuncao.FREELANCER.toString()) {
                    NameTextField(
                        onValueChanged = { name = it },
                        context = context,
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                } else if (userType == UsuarioFuncao.EMPRESA.toString()) {
                    CompanyNameTextField(
                        onValueChanged = { name = it },
                        context = context,
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                }

                Spacer(modifier = Modifier.padding(12.dp))

                if (userType == UsuarioFuncao.FREELANCER.toString()) {
                    CpfTextField({ userDocument = it }, context = context)
                } else if (userType == UsuarioFuncao.EMPRESA.toString()) {
                    CnpjTextField({ userDocument = it }, context = context)
                }

                Spacer(modifier = Modifier.padding(12.dp))

                EmailTextField(onValueChanged = { email = it }, context = context)

                Spacer(modifier = Modifier.padding(12.dp))

                PasswordTextField(onValueChanged = { password = it }, context = context)

                Switch2FA({ isUsing2FA = it }, context = context)

                Spacer(modifier = Modifier.padding(12.dp))

                ProgressButtonCadastro(
                    onClick = { cadastrarUsuario() },
                    text = UiText.StringResource(
                        R.string.btn_text_cadastrar
                    ).asString(context = context),
                    backgroundColor = Color(PrimaryBlue.value),
                    height = (60),
                    width = (350),
                    padding = (10),
                    isLoading = isLoading.observeAsState().value!!,
                )
            }
        }
    }
}

@Composable
fun ProgressButtonCadastro(
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color,
    textColor: Color = Color.White,
    height: Int, padding: Int, width: Int,
    isLoading: Boolean,
) {
    ElevatedButton(
        onClick = { onClick() },
        modifier = Modifier
            .padding(padding.dp)
            .width(width.dp)
            .height(height.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
        ),
        border = BorderStroke(
            1.dp, Color(PrimaryBlue.value)
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        if (isLoading) {
            CircularProgressIndicatorTH(size = 30.0, color = Color.White)
        } else {
            Text(text = text, fontSize = 16.sp, fontWeight = FontWeight(300))
        }
    }
}