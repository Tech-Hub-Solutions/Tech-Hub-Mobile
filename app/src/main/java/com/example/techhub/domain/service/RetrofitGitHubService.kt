package com.example.techhub.domain.service

import com.example.techhub.common.objects.Constants
import com.example.techhub.data.remote.GitHubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitGitHubService {
    private const val BASE_URL = Constants.GITHUB_BASE_URL

    private fun getGitHubClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getGitHubClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getGitHubService(): GitHubApi {
        return getRetrofitInstance().create(GitHubApi::class.java)
    }
}