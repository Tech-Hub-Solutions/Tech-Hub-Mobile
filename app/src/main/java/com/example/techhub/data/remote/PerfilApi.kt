package com.example.techhub.data.remote

import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.domain.model.perfil.PerfilNewArquivo
import com.example.techhub.domain.model.referencia.ReferenciaDetalhadoData
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface PerfilApi {
    @PUT("perfis/favoritar/{idAvaliado}")
    suspend fun favoritarTerceiro(
        @Path("idAvaliado") idAvaliado: Int?,
    ): Response<ReferenciaDetalhadoData>

    @GET("perfis/{idUsuario}")
    suspend fun getPerfil(@Path("idUsuario") idUsuario: Int): Response<PerfilGeralDetalhadoData>

    @PUT("perfis/arquivo")
    @Multipart
    suspend fun atualizarArquivo(
        @Part arquivo: MultipartBody.Part,
        @Part tipoArquivo: MultipartBody.Part
    ): Response<PerfilNewArquivo>

    @PUT("perfis/recomendar/{usuarioId}")
    suspend fun recomendarUsuario(
        @Path("usuarioId") usuarioId: Int
    ): Response<Void>
}