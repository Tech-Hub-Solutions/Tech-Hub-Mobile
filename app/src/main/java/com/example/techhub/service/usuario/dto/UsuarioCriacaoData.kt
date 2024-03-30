package com.example.techhub.service.usuario.dto

import com.example.techhub.utils.enums.UsuarioFuncao


data class UsuarioCriacaoData(
    val nome: String? = null,
    val email: String? = null,
    val senha: String? = null,
    val numeroCadastroPessoa: String? = null,
    val pais: String? = null,
    val funcao: UsuarioFuncao? = null,
    val isUsing2FA: Boolean? = null,
)