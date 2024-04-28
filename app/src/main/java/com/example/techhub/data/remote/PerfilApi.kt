package com.example.techhub.data.remote

import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.domain.model.referencia.ReferenciaDetalhadoData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface PerfilApi {
    @PUT("perfis/favoritar/{idAvaliado}")
    suspend fun favoritarTerceiro(
        @Header("Authorization") authorization: String,
        @Path("idAvaliado") idAvaliado: Int?,
    ): Response<ReferenciaDetalhadoData>

    @GET("perfis/{idUsuario}")
    suspend fun getPerfil(@Path("idUsuario") idUsuario: Int): Response<PerfilGeralDetalhadoData>
}