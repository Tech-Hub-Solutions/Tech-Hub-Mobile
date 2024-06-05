package com.example.techhub.data.remote

import com.example.techhub.domain.model.usuario.Page
import com.example.techhub.domain.model.usuario.UsuarioAtualizacaoData
import com.example.techhub.domain.model.usuario.UsuarioCriacaoData
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UsuarioApi {
    @GET("usuarios/favoritos")
    suspend fun getFavoriteUsers(
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("ordem") ordem: String
    ): Response<Page<UsuarioFavoritoData>>

    @POST("usuarios/filtro")
    suspend fun getTalentos(
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("ordem") ordem: String?,
        @Body filtroData: UsuarioFiltroData
    ): Response<Page<UsuarioFavoritoData>>


    @PUT("usuarios")
    suspend fun atualizarConfigUsuario(@Body usuarioAtualizacaoData: UsuarioAtualizacaoData)
            : Response<UsuarioTokenData>
}