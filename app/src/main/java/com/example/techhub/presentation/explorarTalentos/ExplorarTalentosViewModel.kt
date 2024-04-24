package com.example.techhub.presentation.explorarTalentos

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techhub.common.Constants
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.flag.FlagData
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExplorarTalentosViewModel : ViewModel() {

    val talentos = MutableLiveData(SnapshotStateList<UsuarioFavoritoData>())
    val erroApi = MutableLiveData("")
    val isLoading = MutableLiveData(false)
    val token = Constants.PARAM_TOKEN
    val isLastPage = MutableLiveData(false)

    val flags = MutableLiveData(SnapshotStateList<FlagData>())

    private val usuarioApi = RetrofitService.getUsuarioService()
    private val flagsApi = RetrofitService.getFlagService()

    init {
        getFlags()
    }

    fun getTalentos(page: Int, size: Int, ordem: String, usuarioFiltroData: UsuarioFiltroData) {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = usuarioApi.getTalentos(token, page, size, ordem, usuarioFiltroData)

                if (response.isSuccessful) {
                    val page = response.body()
                    val list = page?.content ?: emptyList()

                    talentos.postValue(SnapshotStateList<UsuarioFavoritoData>().apply {
                        if (page?.number == 0) {
                            clear()
                        }
                        addAll(list)
                    })

                    isLastPage.postValue(page?.last ?: true)

                    erroApi.postValue("")
                } else {
                    erroApi.postValue(response.errorBody()?.toString())
                }
            } catch (e: Exception) {
                Log.e("api", "Ocorreu um erro no get ${e.message}")
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    fun getFlags() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = flagsApi.getFlags(token)

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
                Log.e("api", "Ocorreu um erro no get ${e.message}")
            }
        }
    }

}