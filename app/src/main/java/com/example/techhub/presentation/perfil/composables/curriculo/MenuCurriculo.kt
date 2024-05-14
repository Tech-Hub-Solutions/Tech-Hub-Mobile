package com.example.techhub.presentation.perfil.composables.curriculo

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
import com.example.techhub.common.utils.uriToFile
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
    urlCurriculo: String
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
                    CoroutineScope(Dispatchers.IO).launch {
                        downloadFile(context,urlCurriculo,"seu-curriculo")
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
                    getContent.launch("application/*")
                },
                text = {
                    Text("Subir novo currículo")
                },
                leadingIcon = {
                    Icon(Icons.Filled.FileUpload, contentDescription = "Subir novo currículo")
                }
            )
        }
    }

}

fun downloadFile(context: Context, url: String, fileName: String) {
    val request = DownloadManager.Request(Uri.parse(url))
        .setTitle(fileName)
        .setDescription("Baixando arquivo...")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        .setAllowedOverMetered(true)
        .setAllowedOverRoaming(true)

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)

}