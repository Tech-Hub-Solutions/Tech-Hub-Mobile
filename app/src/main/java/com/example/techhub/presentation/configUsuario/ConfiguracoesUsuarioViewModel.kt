package com.example.techhub.presentation.configUsuario

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
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
    val isLoading = MutableLiveData(false)

    private val usuarioApi = RetrofitService.getUsuarioService()

    fun atualizarConfigUsuario(
        usuarioAtualizacaoData: UsuarioAtualizacaoData,
        context: Context,
    ) {
        isLoading.postValue(true)

        try {
            CoroutineScope(Dispatchers.IO).launch {
                val response = usuarioApi.atualizarConfigUsuario(usuarioAtualizacaoData)
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null) {
                        usuarioTokenData.postValue(body)
                        (context as Activity).runOnUiThread {
                            showToastError(
                                context,
                                UiText.StringResource(
                                    R.string.toast_text_success_atualizar_config_usuario,
                                ).asString(context = context),
                            )
                        }
                    } else {
                        Log.e("PUT - AtualizarConfigUsuario - Error", "Body Nulo")
                        isLoading.postValue(false)
                    }

                    errorApi.postValue("")
                } else {
                    errorApi.postValue(response.errorBody().toString())
                    isLoading.postValue(false)
                }
            }

        } catch (e: Exception) {
            Log.e("PUT - AtualizarConfigUsuario - Error", e.message.toString())
            isLoading.postValue(false)
        }
    }
}