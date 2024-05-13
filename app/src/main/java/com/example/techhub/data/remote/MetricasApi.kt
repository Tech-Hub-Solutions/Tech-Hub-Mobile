package com.example.techhub.data.remote

import com.example.techhub.domain.model.metricas.VisualizacaoPerfilResponseDto
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface MetricasApi {

    @POST("metricas-usuario/{idPerfil}")
    suspend fun registrarMetricasUsuario(
        @Path("idPerfil") idPerfil: Int,
    ): Response<VisualizacaoPerfilResponseDto>
}