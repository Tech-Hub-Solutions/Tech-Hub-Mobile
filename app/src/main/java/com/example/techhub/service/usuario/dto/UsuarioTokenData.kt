package com.example.techhub.service.usuario.dto

import com.example.techhub.utils.enums.UsuarioFuncao

@JvmRecord
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