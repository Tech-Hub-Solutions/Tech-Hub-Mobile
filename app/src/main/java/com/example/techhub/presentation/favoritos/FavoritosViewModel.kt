package com.example.techhub.presentation.favoritos

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import com.example.techhub.common.utils.showToastError
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritosViewModel() {
    val favoritos = MutableLiveData(SnapshotStateList<UsuarioFavoritoData>())
    val erroApi = MutableLiveData("")
    val isLastPage = MutableLiveData(false)
    val isLoading = MutableLiveData(true)
    val totalElements = MutableLiveData(0)
    private val toastErrorMessage = "Ops! Algo deu errado ao buscar favoritos."

    private val usuarioApi = RetrofitService.getUsuarioService()
    private val perfilApi = RetrofitService.getPerfilService()


    fun getFavoriteUsers(page: Int, size: Int, ordem: String, context: Context) {
        if (page == 0) isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = usuarioApi.getFavoriteUsers(page, size, ordem)

                Log.d("GET USUARIOS/FAVORITOS", response.toString())

                if (response.isSuccessful) {
                    val page = response.body()
                    val list = page?.content ?: emptyList()

                    isLastPage.postValue(page?.last ?: false)

                    if (page!!.first) {
                        favoritos.value!!.clear()
                    }

                    favoritos.value!!.addAll(list)
                    totalElements.postValue(page.totalElements.toInt())
                    erroApi.postValue("")
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
                Log.e("GET USUARIOS/FAVORITOS", "Ocorreu um erro no get ${e.message}")
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    fun favoritarUsuario(id: Int?) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = perfilApi.favoritarTerceiro(id)

                if (response.isSuccessful) {
                    Log.d("PUT USUARIOS/FAVORITOS", "Favoritado com sucesso")
                } else {
                    erroApi.postValue(response.errorBody()?.toString())
                }
            } catch (e: Exception) {
                Log.e("PUT USUARIOS/FAVORITOS", "Ocorreu um erro no favoritar ${e.message}")
            }
        }
    }
}

