package com.example.techhub.service.usuario.dto

import com.example.techhub.service.flag.dto.FlagData

@JvmRecord
data class UsuarioFavoritoData(
    val id: Integer? = null,
    val nome: String? = null,
    val descricao: String? = null,
    val qtdEstrela: Double? = null,
    val precoMedio: Double? = null,
    val urlFotoPerfil: String? = null,
    val flags: List<FlagData>? = null,
)
