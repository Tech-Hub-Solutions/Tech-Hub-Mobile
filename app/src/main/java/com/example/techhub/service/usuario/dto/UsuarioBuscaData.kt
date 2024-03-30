package com.example.techhub.service.usuario.dto

@JvmRecord
data class UsuarioBuscaData(
    val id: Integer? = null,
    val nome: String? = null,
    val descricao: String? = null,
    val qtdEstrela: Double? = null,
    val precoMedio: Double? = null,
    val urlFotoPerfil: String? = null
)
