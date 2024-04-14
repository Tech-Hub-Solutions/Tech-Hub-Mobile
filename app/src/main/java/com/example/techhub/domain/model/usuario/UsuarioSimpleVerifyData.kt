package com.example.techhub.domain.model.usuario

data class UsuarioSimpleVerifyData(
    val email: String,
    val senha: String,
    val encodedUrl: String
) : java.io.Serializable
