package com.example.techhub.domain

import android.util.Log
import com.example.techhub.common.Constants
import com.example.techhub.data.remote.AuthApi
import com.example.techhub.data.remote.FlagApi
import com.example.techhub.data.remote.PerfilApi
import com.example.techhub.data.remote.UsuarioApi
import com.example.techhub.domain.model.CurrentUser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private const val BASE_URL = Constants.BASE_URL

    private fun getClient(hasAuthorization: Boolean): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .apply {
                        if (hasAuthorization) {
                            val tokenInterceptor = "Bearer ${CurrentUser.userTokenJwt}"
                            Log.d("ADD INTERCEPTOR", "TOKEN: $tokenInterceptor")
                            addHeader("Authorization", tokenInterceptor)
                        }
                    }
                    .build()
                chain.proceed(newRequest)
            }
            .build()

    }

    private fun getRetrofitInstance(hasAuthorization: Boolean = true): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient(hasAuthorization))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getAuthService(): AuthApi {
        return getRetrofitInstance(false).create(AuthApi::class.java)
    }

    fun getUsuarioService(): UsuarioApi {
        return getRetrofitInstance().create(UsuarioApi::class.java)
    }

    fun getPerfilService(): PerfilApi {
        return getRetrofitInstance().create(PerfilApi::class.java)
    }

    fun getFlagService(): FlagApi {
        return getRetrofitInstance().create(FlagApi::class.java)
    }

}