package com.example.techhub.presentation.editarUsuario

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.showToastError
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.service.RetrofitService
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.domain.model.flag.FlagData
import com.example.techhub.domain.model.perfil.PerfilCadastroData
import com.example.techhub.presentation.perfil.PerfilActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditarUsuarioViewModel : ViewModel() {

    val flags = MutableLiveData(SnapshotStateList<FlagData>())
    private val apiPerfil = RetrofitService.getPerfilService()
    private val erroApi = MutableLiveData("")
    private val flagsApi = RetrofitService.getFlagService()
    val isLoading = MutableLiveData(false)

    fun getFlags() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = flagsApi.getFlags()

                if (response.isSuccessful) {
                    val list = response.body() ?: emptyList()
                    flags.postValue(SnapshotStateList<FlagData>().apply {
                        clear()
                        addAll(list)
                    })
                    erroApi.postValue("")
                } else {
                    erroApi.postValue(response.errorBody()?.toString())
                }
            } catch (e: Exception) {
                Log.e("EDITAR_USUARIO_VIEW_MODEL", "Ocorreu um erro no get ${e.message}")
            }
        }
    }

    fun updateUserInfo(context: Context, perfilCadastroData: PerfilCadastroData) {
        isLoading.postValue(true)

        val toastErrorMessage = UiText.StringResource(
            R.string.toast_text_error_login
        ).asString(context = context)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiPerfil.atualizarPerfil(perfilCadastroData)

                if (response.isSuccessful) {
                    erroApi.postValue("")
                    (context as Activity).runOnUiThread {
                        Toast.makeText(
                            context,
                            UiText.StringResource(
                                R.string.toast_text_perfil_atualizado
                            ).asString(context = context),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    val extras = Bundle()
                    extras.putInt("id", CurrentUser.userProfile!!.id!!)
                    startNewActivity(context, PerfilActivity::class.java, extras)
                } else {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                    erroApi.postValue(response.errorBody()?.toString())
                    isLoading.postValue(false)
                }
            } catch (e: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                isLoading.postValue(false)
                Log.e("EDITAR_USUARIO_VIEW_MODEL", "Ocorreu um erro no get ${e.message}")
            }
        }
    }
}