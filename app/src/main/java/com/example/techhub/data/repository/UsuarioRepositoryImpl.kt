package com.example.techhub.data.repository

import com.example.techhub.data.remote.UsuarioApi
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.example.techhub.domain.repository.UsuarioRepository
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    private val api: UsuarioApi
) : UsuarioRepository {
    override suspend fun login(usuario: UsuarioLoginData): UsuarioTokenData {
        return api.loginUser(usuario)
    }
}