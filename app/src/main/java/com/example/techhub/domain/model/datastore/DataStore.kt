package com.example.techhub.domain.model.datastore

import com.example.techhub.domain.model.usuario.UsuarioTokenData

data class DataStore(
    val userTokenJwt: String = "",
    val urlProfileImage: String = "",
    val userProfile: UsuarioTokenData? = null
)
