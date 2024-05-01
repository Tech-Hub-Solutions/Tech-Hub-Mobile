package com.example.techhub.presentation.editarUsuario

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.flag.FlagData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditarUsuarioViewModel : ViewModel() {

    val flags = MutableLiveData(SnapshotStateList<FlagData>())
    val erroApi = MutableLiveData("")
    private val flagsApi = RetrofitService.getFlagService()
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
                Log.e("api", "Ocorreu um erro no get ${e.message}")
            }
        }
    }
}