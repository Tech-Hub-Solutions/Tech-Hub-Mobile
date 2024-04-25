package com.example.techhub.data.remote

import com.example.techhub.domain.model.flag.FlagData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface FlagApi {
    @GET("flags")
    suspend fun getFlags(
        @Header("Authorization") authorization: String,
    ): Response<List<FlagData>>
}