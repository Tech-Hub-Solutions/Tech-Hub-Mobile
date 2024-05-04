package com.example.techhub.domain.model.perfil


data class PerfilCadastroData(
    val sobreMim: String? = null,
    val experiencia: String? = null,
    val descricao: String? = null,
    val precoMedio: Double? = null,
    val nomeGithub: String? = null,
    val linkGithub: String? = null,
    val linkLinkedin: String? = null,
    val flagsId: MutableList<Int>? = null,
)
