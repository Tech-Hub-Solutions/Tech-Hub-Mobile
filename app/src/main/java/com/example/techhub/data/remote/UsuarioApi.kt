package com.example.techhub.data.remote

import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioApi {
    @POST("usuarios/login")
    fun loginUser(@Body usuario: UsuarioLoginData): Call<UsuarioTokenData>

    @POST("usuarios/verify")
    fun verifyUser(@Body usuario: UsuarioVerifyData): Call<UsuarioTokenData>
}