package com.example.techhub.presentation.perfil

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techhub.common.utils.showToastError
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PerfilViewModel() : ViewModel() {
    private val apiPerfil = RetrofitService.getPerfilService()
    val usuario = MutableLiveData(PerfilGeralDetalhadoData())
    val isLoading = MutableLiveData(true)

    fun getInfosUsuario(context: Context, userId: Int) {
        isLoading.postValue(true)

        val toastErrorMessage = "Ops! Ocorreu um erro em seu perfil."

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiPerfil.getPerfil(idUsuario = userId)

                if (response.isSuccessful) {
                    usuario.postValue(response.body()!!)
                } else {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                }

            } catch (error: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("PERFIL_VIEW_MODEL", "ERROR: ${error.message}")
            } finally {
                isLoading.postValue(false)
            }
        }
    }

}