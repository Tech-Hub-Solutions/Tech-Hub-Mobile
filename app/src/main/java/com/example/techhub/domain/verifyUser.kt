package com.example.techhub.domain

import android.content.Context
import com.example.techhub.common.utils.redirectToPerfilUsuario
import com.example.techhub.common.utils.showToastError
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun verifyUser(
    userData: UsuarioVerifyData,
    context: Context,
    toastErrorMessage: String,
) {
    val usuarioService = RetrofitService.getUsuarioService()

    usuarioService.verifyUser(userData).enqueue(object : Callback<UsuarioTokenData> {
        override fun onResponse(
            call: Call<UsuarioTokenData>,
            response: Response<UsuarioTokenData>
        ) {
            if (response.isSuccessful) {
                redirectToPerfilUsuario(
                    context = context,
                    fullName = response.body()?.nome!!
                )
            } else {
                showToastError(context, toastErrorMessage)
            }
        }

        override fun onFailure(call: Call<UsuarioTokenData>, t: Throwable) {
            showToastError(context, toastErrorMessage)
        }
    })
}
/*
* @Inject
lateinit var dataStoreManager: DataStoreManager
suspend fun verifyUser(
    userData: UsuarioVerifyData,
    context: Context,
    toastErrorMessage: String,
) {
    val usuarioService = RetrofitService.getUsuarioService()

    try {
        val response = usuarioService.verifyUser(userData)

        if (response.isSuccessful) {
            val token = response.body()?.token

            dataStoreManager.saveToDataStore(
                DataStoreManager.USER_TOKEN_JWT,
                UsuarioTokenData(token)
            )
            redirectToPerfilUsuario(
                context = context,
                fullName = response.body()?.nome!!
            )
        } else {
            showToastError(context, toastErrorMessage)
        }
    } catch (e: Exception) {
        showToastError(context, toastErrorMessage)
    }
}

fun verifyUserWithCoroutine(
    userData: UsuarioVerifyData,
    context: Context,
    toastErrorMessage: String,
) {
    CoroutineScope(Dispatchers.IO).launch {
        verifyUser(userData, context, toastErrorMessage)
    }
}
* */