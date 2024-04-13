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
        "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXRhaW5ub3ZhdGVAaG90bWFpbC5jb20iLCJpYXQiOjE3MTMwMzY4MzYsImV4cCI6MTcxNjYzNjgzNn0.tHy7jAvuVnkmus3M9aFDa2yzNNz9rYj0Zg8JSYEMkTbhVlfxLOg6kgZyjtuFrhUgsEERErtrxoIdloZaU2CCRg"

    private val usuarioApi = RetrofitService.getUsuarioService()

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
}
