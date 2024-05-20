package com.example.techhub.presentation.perfil

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techhub.R
import com.example.techhub.common.enums.TipoArquivo
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.showToastError
import com.example.techhub.domain.service.RetrofitService
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.domain.model.avaliacao.AvaliacaoData
import com.example.techhub.domain.model.avaliacao.AvaliacaoTotalData
import com.example.techhub.domain.model.perfil.PerfilAvaliacaoDetalhadoData
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.domain.model.updateFotoPerfil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PerfilViewModel : ViewModel() {
    private val apiPerfil = RetrofitService.getPerfilService()
    private val apiMetricas = RetrofitService.getMetricasService()
    val usuario = MutableLiveData(PerfilGeralDetalhadoData())
    val isLoading = MutableLiveData(true)
    val isLoadingPerfil = MutableLiveData(false)
    val isLoadingWallpaper = MutableLiveData(false)
    val isLoadingCurriculo = MutableLiveData(false)
    val isLastPage = MutableLiveData(false)
    val avaliacoesDoUsuario = MutableLiveData(listOf(AvaliacaoTotalData()))
    val comentariosDoUsuario = MutableLiveData(SnapshotStateList<PerfilAvaliacaoDetalhadoData>())
    val urlCurriculo = MutableLiveData("")

    fun getInfosUsuario(context: Context, userId: Int) {
        isLoading.postValue(true)

        val toastErrorMessage =
            UiText.StringResource(
                R.string.toast_text_error_perfil
            ).asString(context = context)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiPerfil.getPerfil(idUsuario = userId)

                if (response.isSuccessful) {
                    usuario.postValue(response.body()!!)
                    urlCurriculo.postValue(response.body()!!.urlCurriculo)

                    if (response.body()!!.idUsuario != CurrentUser.userProfile?.id) {
                        setVisualizacaoUsuario(context, response.body()!!.idPerfil!!)
                    }

                } else {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                }

            } catch (error: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("PERFIL_VIEW_MODEL", "INFOS USUARIO ERROR: ${error.message}")
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

        val toastErrorMessage = UiText.StringResource(
            R.string.toast_text_error_perfil
        ).asString(context = context)

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
                    Log.e(
                        "PERFIL_VIEW_MODEL",
                        "ATUALIZAR ARQUIVO ERROR: ${response.errorBody()?.string()}"
                    )
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                }
            } catch (error: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("PERFIL_VIEW_MODEL", "ATUALIZAR ARQUIVO ERROR: ${error.message}")
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
        val toastErrorMessage = UiText.StringResource(
            R.string.toast_text_error_favorite_perfil
        ).asString(context = context)

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
                Log.e("PERFIL_VIEW_MODEL", "FAVORITAR PERFIL ERROR: ${error.message}")
            }
        }
    }

    fun recomendarUsuario(context: Context, usuarioId: Int) {
        val toastErrorMessage = UiText.StringResource(
            R.string.toast_text_error_recomendar_perfil
        ).asString(context = context)

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
                Log.e("PERFIL_VIEW_MODEL", "RECOMENDAR PERFIL ERROR: ${error.message}")
            }
        }
    }

    fun getAvaliacoesDoUsuario(context: Context, userId: Int) {
        val toastErrorMessage = UiText.StringResource(
            R.string.toast_text_error_avaliacoes_perfil
        ).asString(context = context)

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
                if (error.message != null) {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                    Log.e("PERFIL_VIEW_MODEL", "AVALIACOES USUARIO ERROR: ${error.message}")
                }
            }
        }
    }

    fun getComentariosDoUsuario(context: Context, userId: Int, page: Int, size: Int) {
        val toastErrorMessage = UiText.StringResource(
            R.string.toast_text_error_comentarios_perfil
        ).asString(context = context)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiPerfil.getComentariosUsuario(userId, page, size)

                if (response.isSuccessful) {
                    val page = response.body()
                    val list = page?.content ?: emptyList()

                    isLastPage.postValue(page?.last ?: false)

                    if (page!!.first) {
                        comentariosDoUsuario.value!!.clear()
                    }

                    comentariosDoUsuario.value!!.addAll(list)
                } else {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                    Log.e(
                        "PERFIL_VIEW_MODEL",
                        "GET COMENTARIO PERFIL ERROR: ${response.errorBody()?.string()}"
                    )
                }
            } catch (error: Exception) {
                if (error.message != null) {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                    Log.e("PERFIL_VIEW_MODEL", "GET COMENTARIO PERFIL ERROR: ${error.message}")
                }
            }
        }
    }

    fun setComentarioUsuario(context: Context, avaliadoId: Int, comment: String, rating: Double) {
        val toastErrorMessage = UiText.StringResource(
            R.string.toast_text_error_comentar_perfil
        ).asString(context = context)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiPerfil.setComentariosUsuario(
                    avaliadoId,
                    AvaliacaoData(
                        comentario = comment,
                        qtdEstrela = rating.toInt()
                    )
                )

                if (response.isSuccessful) {
                    (context as Activity).runOnUiThread {
                        showToastError(
                            context = context,
                            message = UiText.StringResource(
                                R.string.toast_text_success_comentar_perfil
                            ).asString(
                                context = context
                            )
                        )
                    }
                    comentariosDoUsuario.value?.add(0, response.body()!!)
                    getAvaliacoesDoUsuario(context, avaliadoId)
                } else {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                    Log.e(
                        "PERFIL_VIEW_MODEL",
                        "SET COMENTARIO PERFIL ERROR: ${response.errorBody()?.string()}"
                    )
                }
            } catch (error: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("PERFIL_VIEW_MODEL", "SET COMENTARIO PERFIL ERROR: ${error.message}")
            }
        }
    }

    fun setVisualizacaoUsuario(context: Context, perfilId: Int) {
        val toastErrorMessage = "Ops! Ocorreu um erro ao registrar a visualização do perfil."

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiMetricas.registrarMetricasUsuario(perfilId)

                if (response.isSuccessful) {
                    Log.d("PERFIL_VIEW_MODEL", "SET VISUALIZACAO PERFIL SUCCESS")
                    Log.d("PERFIL_VIEW_MODEL", "Body: ${response.body().toString()}")
                }

            } catch (error: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("PERFIL_VIEW_MODEL", "SET VISUALIZACAO PERFIL ERROR: ${error.message}")
            }
        }
    }


    fun enviarCurriculo(context: Context, arquivo: File, tipoArquivo: TipoArquivo) {
        isLoadingCurriculo.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val requestBody = arquivo.asRequestBody("application/pdf".toMediaTypeOrNull())
                val filePart =
                    MultipartBody.Part.createFormData("arquivo", arquivo.name, requestBody)
                val tipoArquivoPart =
                    MultipartBody.Part.createFormData("tipoArquivo", tipoArquivo.name)

                val response = apiPerfil.atualizarArquivo(filePart, tipoArquivoPart)

                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val toastErrorMessage = UiText.StringResource(
                            R.string.toast_text_success_send_fun
                        ).asString(context = context)

                        urlCurriculo.postValue(response.body()!!.url)

                        Log.d(
                            "PERFIL_VIEW_MODEL",
                            "CURRICULO ENVIADO: ${response.body().toString()}"
                        )

                        (context as Activity).runOnUiThread {
                            showToastError(context = context, message = toastErrorMessage)
                        }
                    }
                } else {
                    val toastErrorMessage = UiText.StringResource(
                        R.string.toast_error_send_curriculum_fun
                    ).asString(context = context);

                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                    Log.e(
                        "PERFIL_VIEW_MODEL",
                        "CURRICULO NÃO ENVIADO: ${response.errorBody()?.string()}"
                    )
                }
            } catch (error: Exception) {
                val toastErrorMessage = UiText.StringResource(
                    R.string.toast_error_send_curriculum_fun
                ).asString(context = context)
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("PERFIL_VIEW_MODEL", "ENVIAR CURRICULO ERROR: ${error.message}")
            } finally {
                isLoadingCurriculo.postValue(false)
            }
        }
    }

    fun downloadFile(context: Context, url: String, fileName: String) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val request = DownloadManager.Request(Uri.parse(url))
                        .setTitle(fileName)
                        .setDescription(UiText.StringResource(
                            R.string.description_dowloading_file
                        ).asString(context = context))
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(
                            Environment.DIRECTORY_DOWNLOADS,
                            fileName
                        )
                        .setAllowedOverMetered(true)
                        .setAllowedOverRoaming(true)

                    val downloadManager =
                        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    downloadManager.enqueue(request)

                    val toastErrorMessage = UiText.StringResource(
                        R.string.toast_text_success_download_fun
                    ).asString(context = context)
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }

                    Log.d("PERFIL_VIEW_MODEL", "BAIXAR CURRICULO - SUCCESS")

                } catch (error: Exception) {
                    Log.e("PERFIL_VIEW_MODEL", "BAIXAR CURRICULO ERROR: ${error.message}")
                }
            }

    }
}