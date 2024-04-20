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

    val token =
        "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXRhaW5ub3ZhdGVAaG90bWFpbC5jb20iLCJpYXQiOjE3MTM2MjY2NDUsImV4cCI6MTcxNzIyNjY0NX0.FrUjR0SATSoHOGHa1wasWwD83qxdtXTdp5Je-ZVKk8rXfiK3bTNODeE2FEntZu-CaqRQ8qwWVlR5VBC6uR8kCg"

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

                    favoritos.postValue(SnapshotStateList<UsuarioFavoritoData>().apply {
                        clear()
                        addAll(list)
                    })

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
