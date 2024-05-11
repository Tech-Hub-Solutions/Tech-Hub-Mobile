package com.example.techhub.presentation.configUsuario

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.techhub.common.utils.showToastError
import com.example.techhub.domain.service.RetrofitService
import com.example.techhub.domain.model.usuario.UsuarioAtualizacaoData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfiguracoesUsuarioViewModel {
    val errorApi = MutableLiveData("")
    val usuarioTokenData = MutableLiveData(UsuarioTokenData())

    private val usuarioApi = RetrofitService.getUsuarioService()


    fun atualizarConfigUsuario(
        usuarioAtualizacaoData: UsuarioAtualizacaoData,
        context: Context,
    ) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val response = usuarioApi.atualizarConfigUsuario(usuarioAtualizacaoData)
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null) {
                        usuarioTokenData.postValue(body)
                        (context as Activity).runOnUiThread {
                            showToastError(context, "Suas informações foram atualizadas com sucesso")
                        }
                    } else {
                        Log.e("PUT - AtualizarConfigUsuario - Error", "Body Nulo")
                    }

                    errorApi.postValue("")
                } else {
                    errorApi.postValue(response.errorBody().toString())
                }
            }

        } catch (e: Exception) {
            Log.e("PUT - AtualizarConfigUsuario - Error", e.message.toString())
        }
    }
}