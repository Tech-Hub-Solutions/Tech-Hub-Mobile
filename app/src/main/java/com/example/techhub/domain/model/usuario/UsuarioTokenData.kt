package com.example.techhub.domain.model.usuario

import com.example.techhub.common.enums.UsuarioFuncao


data class UsuarioTokenData(
    val id: Int? = null,
    val nome: String? = null,
    val funcao: UsuarioFuncao? = null,
    val pais: String? = null,
    val urlFotoPerfil: String? = null,
    val isUsing2FA: Boolean? = null,
    val secretQrCodeUrl: String? = null,
    val secret: String? = null,
    val token: String? = null,
) {
}