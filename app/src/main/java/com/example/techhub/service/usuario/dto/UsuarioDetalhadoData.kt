package com.example.techhub.service.usuario.dto

import com.example.techhub.utils.enums.UsuarioFuncao


data class UsuarioDetalhadoData(
    val nome: String? = null,
    val email: String? = null,
    val numeroCadastroPessoa: String? = null,
    val pais: String? = null,
    val funcao: UsuarioFuncao? = null,
)