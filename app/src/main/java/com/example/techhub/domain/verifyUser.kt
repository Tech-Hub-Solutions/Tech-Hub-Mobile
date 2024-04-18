package com.example.techhub.domain

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.techhub.common.utils.redirectToPerfilUsuario
import com.example.techhub.common.utils.showToastError
import com.example.techhub.data.prefdatastore.DataStoreManager
import com.example.techhub.domain.model.datastore.DataStore
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun verifyUser(
    userData: UsuarioVerifyData,
    context: Context,
    toastErrorMessage: String,
) {
    var dataStoreManager = DataStoreManager(context)

    val usuarioService = RetrofitService.getUsuarioService()

    CoroutineScope(Dispatchers.Main).launch {
        try {
            val response = usuarioService.verifyUser(userData)

            if (response.isSuccessful) {
                val token = response.body()?.token

                dataStoreManager.saveToDataStore(
                    DataStore(
                        userTokenJwt = token!!,
                        userProfile = response.body()!!
                    )
                )

                redirectToPerfilUsuario(
                    context = context,
                    fullName = response.body()?.nome!!
                )
            } else {
                (context as Activity).runOnUiThread {
                    showToastError(context, toastErrorMessage)
                }
            }
        } catch (e: Exception) {
            Log.e("VERIFY_USER", "ERROR: ${e.message}")
            (context as Activity).runOnUiThread {
                showToastError(context, toastErrorMessage)
            }
        }
    }
}