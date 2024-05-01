package com.example.techhub.data.remote

import com.example.techhub.domain.model.github.Repositorio
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{username}/repos")
    suspend fun getRepositorios(
        @Path("username") username: String
    ): Response<List<Repositorio>>


    @GET("repos/{username}/{repository}/languages")
    suspend  fun getLanguages(
        @Path("username") username: String,
        @Path("repository") repository: String
    ): Response<Map<String, Int>>
}