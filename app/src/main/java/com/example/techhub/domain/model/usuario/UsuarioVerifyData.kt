package com.example.techhub.domain.model.usuario


data class UsuarioVerifyData(
    val email: String? = null,
    val senha: String? = null,
    val code: String? = null
)