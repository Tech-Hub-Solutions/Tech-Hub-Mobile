package com.example.techhub.data.remote

import com.example.techhub.domain.model.flag.FlagData
import retrofit2.Response
import retrofit2.http.GET

interface FlagApi {
    @GET("flags")
    suspend fun getFlags(): Response<List<FlagData>>
}