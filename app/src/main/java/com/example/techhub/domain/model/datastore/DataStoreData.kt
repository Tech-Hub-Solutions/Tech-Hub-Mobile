package com.example.techhub.domain.model.datastore

import com.example.techhub.domain.model.usuario.UsuarioTokenData

data class DataStoreData(
    val userTokenJwt: String = "",
    val urlProfileImage: String = "",
    val userProfile: UsuarioTokenData? = null,
    val userFuncao: Boolean
)
