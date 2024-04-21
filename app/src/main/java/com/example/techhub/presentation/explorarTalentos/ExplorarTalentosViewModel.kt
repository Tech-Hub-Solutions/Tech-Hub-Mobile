package com.example.techhub.presentation.explorarTalentos

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExplorarTalentosViewModel {

    val talentos = MutableLiveData(SnapshotStateList<UsuarioFavoritoData>())
    val erroApi = MutableLiveData("")
    val token =
        "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtdXJpbG9kc2JfMjAxOUBob3RtYWlsLmNvbSIsImlhdCI6MTcxMzY3NzExMiwiZXhwIjoxNzE3Mjc3MTEyfQ.30e9TtPQlrgbH1sUXRXY_AefRoMn-s5h5CVSQEItJZBUNRorZNCiqDkPx_5gT8iGFktF4e2oTN9xQrskgZ4f_g"

    private val usuarioApi = RetrofitService.getUsuarioService()
    private val perfilApi = RetrofitService.getPerfilService()

    init {
        getTalentos(0, 5, "avaliacao,desc", UsuarioFiltroData())
    }

    fun getTalentos(page: Int, size: Int, ordem: String, usuarioFiltroData: UsuarioFiltroData) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = usuarioApi.getTalentos(token, page, size, ordem, usuarioFiltroData)

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