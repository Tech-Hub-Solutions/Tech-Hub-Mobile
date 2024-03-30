package com.example.techhub.service.usuario.dto

@JvmRecord
data class UsuarioSimpleData(
    val nome: String? = null,
    val email: String? = null,
    val pais: String? = null,
    val funcao: String? = null,
    val isUsing2FA: Boolean? = null,
)