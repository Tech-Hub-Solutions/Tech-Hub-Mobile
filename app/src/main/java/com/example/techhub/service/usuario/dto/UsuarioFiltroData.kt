package com.example.techhub.service.usuario.dto


data class UsuarioFiltroData(
    val nome: String? = null,
    val area: String? = null,
    val tecnologiasIds: List<Int>? = null,
    val precoMax: Double? = null,
    val precoMin: Double? = null,
)
