package com.example.techhub.service.usuario.dto


data class UsuarioVerifyData(
    val email: String? = null,
    val senha: String? = null,
    val code: String? = null
)