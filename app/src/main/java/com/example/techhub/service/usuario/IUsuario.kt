package com.example.techhub.service.usuario

import com.example.techhub.service.usuario.dto.UsuarioLoginData
import com.example.techhub.service.usuario.dto.UsuarioTokenData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IUsuario {
    @POST("usuarios/login")
    fun loginUser(@Body usuario: UsuarioLoginData): Call<UsuarioTokenData>
}