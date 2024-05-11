package com.example.techhub.presentation.editarUsuario

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    val toastErrorMessage = "Ops! Algo deu errado. Tente novamente."

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
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiPerfil.atualizarPerfil(perfilCadastroData)

                if (response.isSuccessful) {
                    erroApi.postValue("")
                    (context as Activity).runOnUiThread {
                        Toast.makeText(
                            context,
                            "Perfil atualizado com sucesso",
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
                }
            } catch (e: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("EDITAR_USUARIO_VIEW_MODEL", "Ocorreu um erro no get ${e.message}")
            }
        }
    }
}