package com.example.techhub.domain.model.usuario


data class UsuarioAtualizacaoData(
    val nome: String? = null,
    val email: String? = null,
    val pais: String? = null,
    val senha: String? = null,
    val isUsing2FA: Boolean? = null,
)
