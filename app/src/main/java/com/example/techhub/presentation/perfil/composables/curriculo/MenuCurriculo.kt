package com.example.techhub.presentation.perfil.composables.curriculo

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.techhub.common.enums.TipoArquivo
import com.example.techhub.common.utils.showToastError
import com.example.techhub.common.utils.uriToFile
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.presentation.perfil.PerfilViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

@Composable
fun MenuCurriculo(
    expanded: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    perfilViewModel: PerfilViewModel,
    context: Context,
    urlCurriculo: String,
    userName: String,
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .padding(5.dp)
    ) {
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
        ) {
            val tipoArquivo = remember { mutableStateOf(TipoArquivo.CURRICULO) }
            val getContent =
                rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { result: Uri? ->
                    result?.let { uri ->
                        val file = uriToFile(context, uri);
                        if (file != null) {
                            perfilViewModel.enviarCurriculo(
                                context,
                                file,
                                tipoArquivo.value
                            )
                        }
                    }
                }

            DropdownMenuItem(
                onClick = {
                    if (urlCurriculo.isNullOrBlank()) {
                        val toastErrorMessage =
                            "O usuário ainda não possui arquivo para download!"
                        (context as Activity).runOnUiThread {
                            showToastError(context = context, message = toastErrorMessage)
                        }
                    } else {
                        perfilViewModel.downloadFile(
                            context,
                            urlCurriculo,
                            "Curriculo de ${userName}"
                        )
                        expanded.value = !expanded.value
                    }
                },
                text = {
                    Text("Baixar currículo")
                },
                leadingIcon = {
                    Icon(Icons.Filled.Download, contentDescription = "Baixar currículo")
                }
            )
            DropdownMenuItem(
                onClick = {
                    tipoArquivo.value = TipoArquivo.CURRICULO
                    getContent.launch("application/pdf")
                },
                text = {
                    Text("Subir novo currículo")
                },
                leadingIcon = {
                    Icon(Icons.Filled.UploadFile, contentDescription = "Subir novo currículo")
                }
            )
        }
    }

}

