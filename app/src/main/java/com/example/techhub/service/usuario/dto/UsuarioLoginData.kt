package com.example.techhub.service.usuario.dto

@JvmRecord
data class UsuarioLoginData(
    val email: String? = null,
    val senha: String? = null
)
