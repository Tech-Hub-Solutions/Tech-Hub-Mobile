package com.example.techhub.service.perfil.dto

@JvmRecord
data class PerfilCadastroData(
    val sobreMim: String? = null,
    val experiencia: String? = null,
    val descricao: String? = null,
    val precoMedio: Double? = null,
    val nomeGithub: String? = null,
    val linkGithub: String? = null,
    val linkLinkedin: String? = null,
    val flagsId: List<Integer>? = null,
)
