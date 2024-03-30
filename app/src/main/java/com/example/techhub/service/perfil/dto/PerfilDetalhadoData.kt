package com.example.techhub.service.perfil.dto

import com.example.techhub.service.flag.dto.FlagData


data class PerfilDetalhadoData(
    val id: Int? = null,
    val sobreMim: String? = null,
    val experiencia: String? = null,
    val descricao: String? = null,
    val precoMedio: Double? = null,
    val nomeGithub: String? = null,
    val linkGithub: String? = null,
    val linkLinkedin: String? = null,
    val flags: List<FlagData>? = null,
)
