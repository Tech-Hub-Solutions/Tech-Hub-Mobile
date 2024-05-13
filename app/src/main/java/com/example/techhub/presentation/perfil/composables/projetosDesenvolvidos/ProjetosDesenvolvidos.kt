package com.example.techhub.presentation.perfil.composables.projetosDesenvolvidos

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.techhub.R
import com.example.techhub.common.composable.CircularProgressIndicatorTH
import com.example.techhub.common.utils.UiText
import com.example.techhub.presentation.perfil.GitHubViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjetosDesenvolvidos(
    nomeGitHub: String?,
    context: Context,
    viewModel: GitHubViewModel
) {
    val repositorios = viewModel.repositorios.observeAsState()
    val responseCode = viewModel.responseCode.observeAsState()
    val isLoading = viewModel.isLoading.observeAsState()

    val text = remember { mutableStateOf("") }

    LaunchedEffect(nomeGitHub) {
        if (!nomeGitHub.isNullOrBlank()) {
            viewModel.getRepositorios(context, nomeGitHub)
        }
    }

    LaunchedEffect(responseCode.value) {
        if (responseCode.value == 403) {
            text.value = UiText.StringResource(
                R.string.text_github_service_limit_exceeded,
            ).asString(context = context)
        } else if (responseCode.value == 404) {
            text.value = UiText.StringResource(
                R.string.text_github_user_not_found,
            ).asString(context = context)
        } else if ((responseCode.value ?: 0) > 404) {
            text.value = UiText.StringResource(
                R.string.text_github_service_error,
            ).asString(context = context)
        } else {
            text.value = ""
        }
    }

    if (text.value.isNotBlank() || nomeGitHub.isNullOrBlank()) {
        Row(
            modifier = Modifier
                .background(Color(0xFFF5F5F5))
                .height(190.dp)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (nomeGitHub.isNullOrBlank())
                    UiText.StringResource(
                        R.string.text_github_not_registered
                    ).asString(context = context)
                else text.value,
                color = Color(0xFF8D8B8B),
                textAlign = TextAlign.Center
            )
        }
    } else if (isLoading.value!!) {
        Row(
            modifier = Modifier
                .background(Color(0xFFF5F5F5))
                .height(190.dp)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicatorTH()
        }
    } else {
        FlowRow(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            repositorios.value!!.forEach { repositorio ->
                GitHubProjectCard(repositorio)
            }
        }
    }
}
