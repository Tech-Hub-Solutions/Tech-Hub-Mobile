package com.example.techhub.presentation.cadastro.composables

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
import androidx.navigation.NavController
import com.example.techhub.common.Screen
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.common.composable.FirstAuthView
import com.example.techhub.common.composable.TopBar
import com.example.techhub.common.utils.copyToClipBoard
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import com.example.techhub.domain.verifyUser
import com.example.techhub.presentation.login.LoginActivity
import com.example.techhub.presentation.ui.theme.GrayButtonText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun CadastroAuthView(
    navController: NavController,
    usuarioSimpleVerifyData: UsuarioSimpleVerifyData
) {
    val secretQrCodeUrl = usuarioSimpleVerifyData.encodedUrl
    val toastErrorMessage = "Erro ao verificar usuário"

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = false,
                title = "Cadastro",
                route = Screen.TravaTelaCadastroView.route,
                navController = navController,
            )
        },
    ) { innerPadding ->
        FirstAuthView(
            usuarioSimpleVerifyData = usuarioSimpleVerifyData,
            secretQrCodeUrl = secretQrCodeUrl,
            toastErrorMessage = toastErrorMessage,
            innerPadding = innerPadding,
            cancelarActivity = LoginActivity::class.java
        )
    }
}