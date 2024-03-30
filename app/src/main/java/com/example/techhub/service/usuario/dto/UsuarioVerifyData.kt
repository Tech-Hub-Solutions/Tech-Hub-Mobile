package com.example.techhub.service.usuario.dto

@JvmRecord
data class UsuarioVerifyData(
    val email: String? = null,
    val senha: String? = null,
    val code: String? = null
)