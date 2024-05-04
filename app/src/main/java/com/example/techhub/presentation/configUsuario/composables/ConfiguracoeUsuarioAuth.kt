package com.example.techhub.presentation.configUsuario.composables

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.techhub.common.Screen
import com.example.techhub.common.composable.FirstAuthView
import com.example.techhub.common.composable.TopBar
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.presentation.login.LoginActivity
import com.example.techhub.presentation.perfil.PerfilActivity

@Composable
fun ConfiguracoeUsuarioAuth(
    navController: NavController,
    usuarioSimpleVerifyData: UsuarioSimpleVerifyData?
) {
    val secretQrCodeUrl = usuarioSimpleVerifyData!!.encodedUrl
    val toastErrorMessage = "Erro ao verificar usuário"

    Scaffold(
        topBar = {
//            TopBar(
//                willRedirectToActivity = true,
//                title = "Editar perfil",
//                route = Screen.TravaTelaCadastroView.route,
//                navController = navController,
//            )
        },
    ) { innerPadding ->
        FirstAuthView(
            usuarioSimpleVerifyData = usuarioSimpleVerifyData,
            secretQrCodeUrl = secretQrCodeUrl,
            toastErrorMessage = toastErrorMessage,
            innerPadding = innerPadding,
            cancelarActivity = PerfilActivity::class.java
        )
    }
}