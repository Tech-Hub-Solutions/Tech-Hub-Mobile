package com.example.techhub.data.remote

import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioApi {
    @POST("usuarios/login")
    fun loginUser(@Body usuario: UsuarioLoginData): Call<UsuarioTokenData>
}