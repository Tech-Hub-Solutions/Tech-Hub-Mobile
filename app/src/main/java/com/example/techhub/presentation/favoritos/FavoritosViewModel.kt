package com.example.techhub.presentation.favoritos

import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import com.example.techhub.data.prefdatastore.DataStoreManager
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritosViewModel(context: Context?=null) {
    var dataStoreManager = DataStoreManager(context!!)
    val favoritos = MutableLiveData(SnapshotStateList<UsuarioFavoritoData>())
    val erroApi = MutableLiveData("")
    val isLastPage = MutableLiveData(false)

    private val token = MutableLiveData("")

    private val usuarioApi = RetrofitService.getUsuarioService()
    private val perfilApi = RetrofitService.getPerfilService()

    init {
        getTokenJWT()
        getFavoriteUsers(0, 10, "", "avaliacao,desc")
    }

    fun getFavoriteUsers(page: Int, size: Int, sort: String, ordem: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = usuarioApi.getFavoriteUsers(token.value.toString(), page, size, sort, ordem)

                Log.d("GET USUARIOS/FAVORITOS", response.toString())

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
                Log.e("GET USUARIOS/FAVORITOS", "Ocorreu um erro no get ${e.message}")
            }
        }
    }

    fun favoritarUsuario(id: Int?) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = perfilApi.favoritarTerceiro(token.value.toString(), id)

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

    fun getTokenJWT() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreManager.getFromDataStore().collect{
                token.postValue("Bearer " + it.userTokenJwt)
                Log.d("TOKEN PRINT", it.userTokenJwt)
            }
        }
    }
}

