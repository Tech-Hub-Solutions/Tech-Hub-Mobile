package com.example.techhub.presentation.explorarTalentos

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExplorarTalentosViewModel {

    val talentos = MutableLiveData(SnapshotStateList<UsuarioFavoritoData>())
    val erroApi = MutableLiveData("")
    val token =
        "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXRhaW5ub3ZhdGVAaG90bWFpbC5jb20iLCJpYXQiOjE3MTMyOTA4NzUsImV4cCI6MTcxNjg5MDg3NX0.dLE-ba9nQ3cpHPa503nDPgtNXn6KPH3GUrRU-AU3vWjN_4L-wYw7Y8b7KANT7KWDjJaQVpUE4WSlbqmqOdO2bw"

    private val usuarioApi = RetrofitService.getUsuarioService()
    private val perfilApi = RetrofitService.getPerfilService()

    init {
        getTalentos(0, 5, "", "avaliacao,desc")
    }

    fun getTalentos(page: Int, size: Int, sort: String, ordem: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = usuarioApi.getTalentos(token, page, size, sort, ordem)



                if (response.isSuccessful) {
                    val page = response.body()
                    val list = page?.content ?: emptyList()

                    talentos.postValue(SnapshotStateList<UsuarioFavoritoData>().apply {
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