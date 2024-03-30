package com.example.techhub.service.usuario.dto


data class UsuarioBuscaData(
    val id: Int? = null,
    val nome: String? = null,
    val descricao: String? = null,
    val qtdEstrela: Double? = null,
    val precoMedio: Double? = null,
    val urlFotoPerfil: String? = null
)
