package com.example.techhub.data.remote

import com.example.techhub.domain.model.avaliacao.AvaliacaoData
import com.example.techhub.domain.model.avaliacao.AvaliacaoTotalData
import com.example.techhub.domain.model.perfil.PerfilAvaliacaoDetalhadoData
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.domain.model.perfil.PerfilNewArquivo
import com.example.techhub.domain.model.referencia.ReferenciaDetalhadoData
import com.example.techhub.domain.model.usuario.Page
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

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
        @Part tipoArquivo: MultipartBody.Part,
    ): Response<PerfilNewArquivo>

    @PUT("perfis/recomendar/{usuarioId}")
    suspend fun recomendarUsuario(
        @Path("usuarioId") usuarioId: Int,
    ): Response<Void>

    @GET("perfis/avaliacao/geral/{usuarioId}")
    suspend fun getAvaliacoesUsuario(
        @Path("usuarioId") usuarioId: Int,
    ): Response<List<AvaliacaoTotalData>>

    @GET("perfis/avaliacao/{userId}")
    suspend fun getComentariosUsuario(
        @Path("userId") userId: Int,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("sort") sort: String = "id,desc"
    ): Response<Page<PerfilAvaliacaoDetalhadoData>>

    @POST("perfis/avaliacao/{avaliadoId}")
    suspend fun setComentariosUsuario(
        @Path("avaliadoId") avaliadoId: Int,
        @Body avaliacaoData : AvaliacaoData
    ): Response<PerfilAvaliacaoDetalhadoData>
}