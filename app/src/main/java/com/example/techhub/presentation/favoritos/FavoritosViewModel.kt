package com.example.techhub.presentation.favoritos

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritosViewModel {
    val favoritos = MutableLiveData(SnapshotStateList<UsuarioFavoritoData>())
    val erroApi = MutableLiveData("")
    val isLastPage = MutableLiveData(false)

    val token =
        "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtdXJpbG9kc2JfMjAxOUBob3RtYWlsLmNvbSIsImlhdCI6MTcxMzY3NzExMiwiZXhwIjoxNzE3Mjc3MTEyfQ.30e9TtPQlrgbH1sUXRXY_AefRoMn-s5h5CVSQEItJZBUNRorZNCiqDkPx_5gT8iGFktF4e2oTN9xQrskgZ4f_g"

    private val usuarioApi = RetrofitService.getUsuarioService()
    private val perfilApi = RetrofitService.getPerfilService()

    init {
        getFavoriteUsers(0, 10, "", "avaliacao,desc")
    }

    fun getFavoriteUsers(page: Int, size: Int, sort: String, ordem: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = usuarioApi.getFavoriteUsers(token, page, size, sort, ordem)

                Log.d("RESPONSE AQ", response.toString())

                if (response.isSuccessful) {
                    val page = response.body()
                    val list = page?.content ?: emptyList()

                    isLastPage.postValue(page?.last ?: false)

                    if (page?.first == true) {
                        favoritos.value!!.clear()
                    }

                    favoritos.value!!.addAll(list)

                    erroApi.value = ""
                } else {
                    erroApi.postValue(response.errorBody()?.toString())
                }
            } catch (e: Exception) {
                Log.e("api", "Ocorreu um erro no get ${e.message}")
            }
        }
    }

    fun favoritarUsuario(id: Int?) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = perfilApi.favoritarTerceiro(token, id)

                if (response.isSuccessful) {
                    Log.d("API PERFIL", "Favoritado com sucesso")
                } else {
                    erroApi.postValue(response.errorBody()?.toString())
                }
            } catch (e: Exception) {
                Log.e("api", "Ocorreu um erro no favoritar ${e.message}")
            }
        }
    }
}
