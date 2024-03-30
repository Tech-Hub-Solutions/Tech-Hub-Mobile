package com.example.techhub.service.avaliacao.dto

@JvmRecord
data class AvaliacaoDetalhadoData(
    val id: Int? = null,
    val nomePerfil: String? = null,
    val idAvaliador: Integer? = null,
    val avaliador: String? = null,
    val urlFotoPerfil: String? = null,
    val pais: String? = null,
    val comentario: String? = null,
    val qtdEstrela: Integer? = null,
)
