package com.example.techhub.service

import com.example.techhub.service.usuario.IUsuario
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    /* TODO - Alterar o endereço de IP para o endereço do servidor
    * Estaremos utilizando o endereço de IP, pois
    * estamos utilizando o microserviço localmente.
    * Depois, alterar para o endereço do microserviço no servidor.
    * */
    private val BASE_URL = "http://127.0.0.1:8080/api/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(newRequest)
        }
        .build()

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun loginUser(): IUsuario {
        return getRetrofitInstance().create(IUsuario::class.java)
    }
}