package com.example.techhub.service.avaliacao.dto


data class AvaliacaoDetalhadoData(
    val id: Int? = null,
    val nomePerfil: String? = null,
    val idAvaliador: Int? = null,
    val avaliador: String? = null,
    val urlFotoPerfil: String? = null,
    val pais: String? = null,
    val comentario: String? = null,
    val qtdEstrela: Int? = null,
)
