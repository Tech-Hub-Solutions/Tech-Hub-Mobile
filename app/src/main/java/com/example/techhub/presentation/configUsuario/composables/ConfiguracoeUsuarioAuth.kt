package com.example.techhub.presentation.configUsuario.composables

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.techhub.R
import com.example.techhub.common.composable.FirstAuthView
import com.example.techhub.common.utils.UiText
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.presentation.perfil.PerfilActivity

@Composable
fun ConfiguracoeUsuarioAuth(
    usuarioSimpleVerifyData: UsuarioSimpleVerifyData?
) {
    val secretQrCodeUrl = usuarioSimpleVerifyData!!.encodedUrl
    val context = LocalContext.current
    val toastErrorMessage =
        UiText.StringResource(
            R.string.toast_auth_text_error
        ).asString(context = context)

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