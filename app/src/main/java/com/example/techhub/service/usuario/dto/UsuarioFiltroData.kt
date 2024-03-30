package com.example.techhub.service.usuario.dto

@JvmRecord
data class UsuarioFiltroData(
    val nome: String? = null,
    val area: String? = null,
    val tecnologiasIds: List<Integer>? = null,
    val precoMax: Double? = null,
    val precoMin: Double? = null,
)
