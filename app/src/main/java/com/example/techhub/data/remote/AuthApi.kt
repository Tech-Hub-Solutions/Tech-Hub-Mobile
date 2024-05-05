package com.example.techhub.data.remote

import com.example.techhub.domain.model.usuario.UsuarioCriacaoData
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @POST("usuarios/login")
    suspend fun loginUser(@Body user: UsuarioLoginData): Response<UsuarioTokenData>

    @POST("usuarios/verify")
    suspend fun verifyUser(@Body usuario: UsuarioVerifyData): Response<UsuarioTokenData>

    @POST("usuarios")
    fun cadastrarUsuario(@Body usuario: UsuarioCriacaoData): Call<UsuarioTokenData>
}