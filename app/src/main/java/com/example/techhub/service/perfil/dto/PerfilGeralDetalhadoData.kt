package com.example.techhub.service.perfil.dto

import com.example.techhub.service.flag.dto.FlagData
import com.example.techhub.utils.enums.UsuarioFuncao


data class PerfilGeralDetalhadoData(
    val idUsuario: Int? = null,
    val idPerfil: Int? = null,
    val urlFotoPerfil: String? = null,
    val urlFotoWallpaper: String? = null,
    val urlCurriculo: String? = null,
    val nome: String? = null,
    val email: String? = null,
    val pais: String? = null,
    val funcao: UsuarioFuncao? = null,
    val sobreMim: String? = null,
    val experiencia: String? = null,
    val descricao: String? = null,
    val precoMedio: Double? = null,
    val nomeGithub: String? = null,
    val linkGithub: String? = null,
    val linkLinkedin: String? = null,
    val flags: List<FlagData>? = null,
    val isFavorito: Boolean? = null,
    val qtdFavoritos: Long? = null,
    val isRecomendado: Boolean? = null,
    val qtdRecomendacoes: Long? = null,
)
