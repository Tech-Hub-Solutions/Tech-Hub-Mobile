package com.example.techhub.presentation.login

import com.example.techhub.common.Resource
import com.example.techhub.domain.model.usuario.UsuarioTokenData

data class LoginState(
    val isLoading: Boolean = false,
    val error: String = "",
    val usuarioToken: UsuarioTokenData? = null,
)
