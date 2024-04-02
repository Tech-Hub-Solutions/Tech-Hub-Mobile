package com.example.techhub.domain.model.usuario

import com.example.techhub.domain.model.flag.FlagData


data class UsuarioFavoritoData(
    val id: Int? = null,
    val nome: String? = null,
    val descricao: String? = null,
    val qtdEstrela: Double? = null,
    val precoMedio: Double? = null,
    val urlFotoPerfil: String? = null,
    val flags: List<FlagData>? = null,
)
