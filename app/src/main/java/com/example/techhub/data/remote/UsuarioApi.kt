package com.example.techhub.data.remote

import com.example.techhub.domain.model.usuario.Page
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UsuarioApi {
    @POST("usuarios/login")
    fun loginUser(@Body usuario: UsuarioLoginData): Call<UsuarioTokenData>

    @POST("usuarios/verify")
    fun verifyUser(@Body usuario: UsuarioVerifyData): Call<UsuarioTokenData>

    @GET("usuarios/favoritos")
    suspend fun getFavoriteUsers(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("sort") sort: String?,
        @Query("ordem") ordem: String
    ) : Response<Page<UsuarioFavoritoData>>

    @GET("usuarios/explorarTalentos")
    suspend fun getTalentos(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("sort") sort: String?,
        @Query("ordem") ordem: String
    ) : Response<Page<UsuarioFavoritoData>>
}