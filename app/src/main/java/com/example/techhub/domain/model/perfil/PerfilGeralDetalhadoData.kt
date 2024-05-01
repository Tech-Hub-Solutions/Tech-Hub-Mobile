package com.example.techhub.domain.model.perfil

import com.example.techhub.domain.model.flag.FlagData
import com.example.techhub.common.enums.UsuarioFuncao


data class PerfilGeralDetalhadoData(
    var idUsuario: Int? = null,
    var idPerfil: Int? = null,
    var urlFotoPerfil: String? = null,
    var urlFotoWallpaper: String? = null,
    var urlCurriculo: String? = null,
    var nome: String? = null,
    var email: String? = null,
    var pais: String? = null,
    var funcao: UsuarioFuncao? = null,
    var sobreMim: String? = null,
    var experiencia: String? = null,
    var descricao: String? = null,
    var precoMedio: Double? = null,
    var nomeGithub: String? = null,
    var linkGithub: String? = null,
    var linkLinkedin: String? = null,
    var flags: List<FlagData>? = null,
    var isFavorito: Boolean? = null,
    var qtdFavoritos: Long? = null,
    var isRecomendado: Boolean? = null,
    var qtdRecomendacoes: Long? = null,
)
