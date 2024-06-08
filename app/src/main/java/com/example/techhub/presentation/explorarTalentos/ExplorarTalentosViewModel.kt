package com.example.techhub.presentation.explorarTalentos

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techhub.domain.service.RetrofitService
import com.example.techhub.domain.model.flag.FlagData
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExplorarTalentosViewModel : ViewModel() {
    val talentos = MutableLiveData(SnapshotStateList<UsuarioFavoritoData>())
    val erroApi = MutableLiveData("")
    val totalElements = MutableLiveData(0)
    val isLoading = MutableLiveData(true)
    val isloadingMoreTalents = MutableLiveData(false)
    val isLastPage = MutableLiveData(false)

    val flags = MutableLiveData(SnapshotStateList<FlagData>())

    private val usuarioApi = RetrofitService.getUsuarioService()
    private val flagsApi = RetrofitService.getFlagService()

    fun getTalentos(page: Int, size: Int, ordem: String, usuarioFiltroData: UsuarioFiltroData) {
        if (page == 0) isLoading.postValue(true) else isloadingMoreTalents.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = usuarioApi.getTalentos(page, size, ordem, usuarioFiltroData)

                if (response.isSuccessful) {
                    val responsePage = response.body()
                    val list = responsePage?.content ?: emptyList()

                    if (responsePage!!.first) {
                        talentos.value!!.clear()
                    }
                    talentos.value!!.addAll(list)

                    isLastPage.postValue(responsePage.last)
                    totalElements.postValue(responsePage.totalElements.toInt())


                    erroApi.postValue("")
                } else {
                    erroApi.postValue(response.errorBody()?.toString())
                }
            } catch (e: Exception) {
                Log.e(
                    "EXPLORAR_TALENTOS_VIEW_MODEL",
                    "Ocorreu um erro no GET talentos $e"
                )
                talentos.value!!.clear()
                totalElements.postValue(0)
                isLastPage.postValue(true)

            } finally {
                isLoading.postValue(false)
                isloadingMoreTalents.postValue(false)
            }
        }
    }

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
                Log.e("EXPLORAR_TALENTOS_VIEW_MODEL", "Ocorreu um erro no GET Flags ${e.message}")
            }
        }
    }

}