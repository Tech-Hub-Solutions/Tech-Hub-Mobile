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

    // ⚠️ sempre trocar o IP para o IP da sua máquina que aparece quando executa "ipconfig" no terminal
    private const val IP = "192.168.15.60"
    private val BASE_URL = "http://${IP}:8080/api/"

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

    fun getUsuarioService(): IUsuario {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IUsuario::class.java)
    }
}