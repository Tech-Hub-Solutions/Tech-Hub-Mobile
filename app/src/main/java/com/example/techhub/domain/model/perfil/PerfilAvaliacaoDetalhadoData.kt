package com.example.techhub.domain.model.perfil

data class PerfilAvaliacaoDetalhadoData(
    val id: Int? = null,
    val nome: String? = null,
    val idAvaliador: Int? = null,
    val avaliador: String? = null,
    val urlFotoPerfil: String? = null,
    val pais: String? = null,
    val comentario: String? = null,
    val qtdEstrela: Integer? = null,
)
