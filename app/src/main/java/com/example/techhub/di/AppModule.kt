package com.example.techhub.di

import com.example.techhub.common.Constants
import com.example.techhub.data.remote.UsuarioApi
import com.example.techhub.data.repository.UsuarioRepositoryImpl
import com.example.techhub.domain.repository.UsuarioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// Um módulo tem a função de prover dependências para o grafo de dependências
@Module

// InstallIn faz com que o módulo seja instalado em um componente específico
// SinfletonComponent é um componente que dura por toda a vida da aplicação, se ela for encerrada, o componente também é
@InstallIn(SingletonComponent::class)
object AppModule {
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

    // Todos módulos provêm dependências, então é necessário anotar com
    @Provides
    // Singleton significa que haverá apenas uma instância dessa dependência
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideUsuarioApi(): UsuarioApi {
        return getRetrofitInstance().create(UsuarioApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUsuarioRepository(api: UsuarioApi): UsuarioRepository {
        return UsuarioRepositoryImpl(api)
    }
}