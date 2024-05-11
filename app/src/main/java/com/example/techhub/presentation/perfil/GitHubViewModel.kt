package com.example.techhub.presentation.perfil

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techhub.common.utils.showToastError
import com.example.techhub.domain.service.RetrofitGitHubService
import com.example.techhub.domain.model.github.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GitHubViewModel : ViewModel() {
    val apiGitHub = RetrofitGitHubService.getGitHubService()
    val repositorios = MutableLiveData(listOf<Repositorio>())
    val responseCode = MutableLiveData<Int>()
    val isLoading = MutableLiveData(true)

    fun getRepositorios(context: Context, username: String) {
        isLoading.postValue(true)
        val toastErrorMessage = "Ops! Ocorreu um erro ao buscar os repositórios."

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiGitHub.getRepositorios(username)

                if (response.isSuccessful) {
                    val repos = response.body()!!
                    repos.forEach {
                        it.languages = listOf(it.language ?: "")
                    }
                    repositorios.postValue(repos)
                }

                responseCode.postValue(response.code())
            } catch (error: Exception) {
                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
                Log.e("GITHUB_VIEW_MODEL", "ERROR: ${error.message}")
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}