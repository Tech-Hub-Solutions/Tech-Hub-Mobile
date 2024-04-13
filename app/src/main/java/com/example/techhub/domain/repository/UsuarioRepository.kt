package com.example.techhub.domain.repository

import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioTokenData

interface UsuarioRepository {
    suspend fun login(usuario: UsuarioLoginData): UsuarioTokenData

}