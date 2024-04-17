package com.example.techhub.domain.model.perfil

import com.example.techhub.domain.model.flag.FlagData


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
