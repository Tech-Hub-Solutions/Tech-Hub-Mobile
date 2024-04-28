package com.example.techhub.domain

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.techhub.common.enums.UsuarioFuncao
import com.example.techhub.common.utils.redirectToPerfilUsuario
import com.example.techhub.common.utils.showToastError
import com.example.techhub.data.prefdatastore.DataStoreManager
import com.example.techhub.domain.model.datastore.DataStoreData
import com.example.techhub.domain.model.updateCurrentUser
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun verifyUser(
    userData: UsuarioVerifyData,
    context: Context,
    toastErrorMessage: String,
) {
    val dataStoreManager = DataStoreManager(context)

    val usuarioService = RetrofitService.getUsuarioService()

    CoroutineScope(Dispatchers.Main).launch {
        try {
            val response = usuarioService.verifyUser(userData)

            if (response.isSuccessful) {
                val token = response.body()?.token
                Log.d("RESPONSE_BODY ==> ", response.body().toString())

                dataStoreManager.saveToDataStore(
                    DataStoreData(
                        userTokenJwt = token!!,
                        userProfile = response.body()!!,
                        userFuncao = response.body()!!.funcao == UsuarioFuncao.EMPRESA
                    )
                )

                updateCurrentUser(
                    context = context,
                    usuarioTokenData = response.body()!!
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