package com.example.techhub.domain

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.techhub.common.Constants
import com.example.techhub.common.enums.UsuarioFuncao
import com.example.techhub.data.prefdatastore.DataStoreManager
import com.example.techhub.data.remote.FlagApi
import com.example.techhub.data.remote.PerfilApi
import com.example.techhub.data.remote.UsuarioApi
import com.example.techhub.domain.model.datastore.DataStoreData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private const val BASE_URL = Constants.BASE_URL
    private var token = ""
    fun updateTokenJwt(context: Context, usuarioTokenData: UsuarioTokenData) {
        val dataStoreManager = DataStoreManager(context)

        CoroutineScope(Dispatchers.Main).launch {
            Log.d("UPDATE_TOKEN", "TOKEN: $token")
            dataStoreManager.saveToDataStore(
                DataStoreData(
                    userTokenJwt = usuarioTokenData.token!!,
                    userProfile = usuarioTokenData,
                    userFuncao = usuarioTokenData.funcao == UsuarioFuncao.EMPRESA
                )
            )
            token = usuarioTokenData.token
        }
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun getIsUserEmpresa(context: Context): Boolean {
        val dataStoreManager = DataStoreManager(context)
        var isEmpresa by remember { mutableStateOf(false) }

        CoroutineScope(Dispatchers.Main).launch {
            dataStoreManager.getFromDataStore().collect { value ->
                isEmpresa = value.userFuncao
            }
        }

        return isEmpresa
    }

    fun updateTokenJwt(token: String) {
        this.token = token
    }

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
                            val tokenInterceptor = "Bearer $token"
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