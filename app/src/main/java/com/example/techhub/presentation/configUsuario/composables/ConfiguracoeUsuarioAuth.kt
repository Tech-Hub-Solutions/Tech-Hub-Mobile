package com.example.techhub.presentation.configUsuario.composables

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.techhub.common.composable.FirstAuthView
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.presentation.perfil.PerfilActivity

@Composable
fun ConfiguracoeUsuarioAuth(
    usuarioSimpleVerifyData: UsuarioSimpleVerifyData?
) {
    val secretQrCodeUrl = usuarioSimpleVerifyData!!.encodedUrl
    val toastErrorMessage = "Erro ao verificar usuário"

    Scaffold { innerPadding ->
        FirstAuthView(
            usuarioSimpleVerifyData = usuarioSimpleVerifyData,
            secretQrCodeUrl = secretQrCodeUrl,
            toastErrorMessage = toastErrorMessage,
            innerPadding = innerPadding,
            cancelarActivity = PerfilActivity::class.java
        )
    }
}