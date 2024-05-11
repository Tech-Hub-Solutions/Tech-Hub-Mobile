package com.example.techhub.domain.model.usuario


data class UsuarioFiltroData(
    val nome: String? = null,
    val area: String? = null,
    val tecnologiasIds: MutableList<Int>? = null,
    val precoMax: Float? = null,
    val precoMin: Float? = null,
)
