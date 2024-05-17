package com.example.techhub.presentation.cadastro.composables

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.techhub.R
import com.example.techhub.common.enums.Screen
import com.example.techhub.common.composable.FirstAuthView
import com.example.techhub.common.composable.TopBar
import com.example.techhub.common.utils.UiText
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.presentation.login.LoginActivity

@Composable
fun CadastroAuthView(
    navController: NavController,
    usuarioSimpleVerifyData: UsuarioSimpleVerifyData,
) {
    val secretQrCodeUrl = usuarioSimpleVerifyData.encodedUrl
    val context = LocalContext.current
    val toastErrorMessage = UiText.StringResource(
        R.string.toast_text_error_cadastro_auth
    ).asString(context = context)

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = false,
                title = UiText.StringResource(
                    R.string.title_cadastro
                ).asString(context = context),
                route = Screen.TravaTelaCadastroView.route,
                navController = navController,
                context = context
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