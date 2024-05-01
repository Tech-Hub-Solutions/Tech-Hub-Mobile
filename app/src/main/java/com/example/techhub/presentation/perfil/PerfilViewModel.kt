package com.example.techhub.presentation.perfil

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techhub.common.enums.TipoArquivo
import com.example.techhub.common.utils.showToastError
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.domain.model.avaliacao.AvaliacaoTotalData
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.domain.model.updateFotoPerfil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PerfilViewModel : ViewModel() {
    private val apiPerfil = RetrofitService.getPerfilService()
    val usuario = MutableLiveData(PerfilGeralDetalhadoData())
    val isLoading = MutableLiveData(true)
    val isLoadingPerfil = MutableLiveData(false)
    val isLoadingWallpaper = MutableLiveData(false)
    val avaliacoesDoUsuario = MutableLiveData(listOf(AvaliacaoTotalData()))

    fun getInfosUsuario(context: Context, userId: Int) {
        isLoading.postValue(true)

        val toastErrorMessage = "Ops! Ocorreu um erro em seu perfil."

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiPerfil.getPerfil(idUsuario = userId)

                if (response.isSuccessful) {
                    usuario.postValue(response.body()!!)
                } else {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                }

            } catch (error: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("PERFIL_VIEW_MODEL", "ERROR: ${error.message}")
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    fun atualizarArquivo(context: Context, arquivo: File, tipoArquivo: TipoArquivo) {
        if (tipoArquivo == TipoArquivo.WALLPAPER) {
            isLoadingWallpaper.postValue(true)
        } else {
            isLoadingPerfil.postValue(true)
        }

        val toastErrorMessage = "Ops! Ocorreu um erro em seu perfil."

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val requestBody = arquivo.asRequestBody("image/*".toMediaTypeOrNull())
                val filePart =
                    MultipartBody.Part.createFormData("arquivo", arquivo.name, requestBody)
                val tipoArquivoPart =
                    MultipartBody.Part.createFormData("tipoArquivo", tipoArquivo.name)

                val response = apiPerfil.atualizarArquivo(filePart, tipoArquivoPart)

                if (response.isSuccessful) {
                    val url = response.body()!!.url

                    usuario.postValue(usuario.value!!.apply {
                        if (tipoArquivo == TipoArquivo.PERFIL) {
                            CurrentUser.urlProfileImage = url
                            updateFotoPerfil(context, url ?: "")
                            urlFotoPerfil = url
                        } else {
                            urlFotoWallpaper = url
                            usuario.value!!.urlFotoPerfil
                        }

                    })

                } else {
                    Log.d("PERFIL_VIEW_MODEL", "ERROR: ${response.errorBody()?.string()}")
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                }

            } catch (error: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("PERFIL_VIEW_MODEL", "ERROR: ${error.message}")
            } finally {
                if (tipoArquivo == TipoArquivo.WALLPAPER) {
                    isLoadingWallpaper.postValue(false)
                } else {
                    isLoadingPerfil.postValue(false)
                }
            }
        }
    }

    fun favoritarPerfil(context: Context, idUsuario: Int) {
        val toastErrorMessage = "Ops! Ocorreu um erro ao favoritar o perfil."

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiPerfil.favoritarTerceiro(idUsuario)

                if (!response.isSuccessful) {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                }

            } catch (error: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("PERFIL_VIEW_MODEL", "ERROR: ${error.message}")
            }
        }
    }

    fun recomendarUsuario(context:Context, usuarioId: Int) {
        val toastErrorMessage = "Ops! Ocorreu um erro ao recomendar o perfil."

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiPerfil.recomendarUsuario(usuarioId)

                if (!response.isSuccessful) {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                }

            } catch (error: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("PERFIL_VIEW_MODEL", "ERROR: ${error.message}")
            }
        }
    }

    fun getAvaliacoesDoUsuario(context: Context, userId: Int) {
        val toastErrorMessage = "Ops! Ocorreu um erro ao buscar as avaliações do perfil."

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiPerfil.getAvaliacoesUsuario(userId)

                if (response.isSuccessful) {
                    avaliacoesDoUsuario.postValue(response.body()!!)
                } else {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                }

            } catch (error: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("PERFIL_VIEW_MODEL", "ERROR: ${error.message}")
            }
        }
    }

}