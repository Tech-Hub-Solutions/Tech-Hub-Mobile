package com.example.techhub.presentation.perfil.composables

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import com.example.techhub.common.enums.TipoArquivo
import com.example.techhub.presentation.perfil.PerfilViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

@Composable
fun MenuEditarImagens(
    expanded: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    perfilViewModel: PerfilViewModel,
    context: Context
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
            val getContent =
                rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { result: Uri? ->
                    result?.let { uri ->
                        val inputStream = context.contentResolver.openInputStream(uri)
                        val file = getFileName(context, uri)?.let { File(context.cacheDir, it) }
                        val outputStream = FileOutputStream(file)
                        inputStream.use { input ->
                            outputStream.use { output ->
                                input!!.copyTo(output)
                            }
                        }
                        if (file != null) {
                            perfilViewModel.atualizarArquivo(context, file, TipoArquivo.PERFIL)
                        }
                    }
                }

            DropdownMenuItem(
                onClick = {
                    getContent.launch("image/*")
                },
                text = {
                    Text("Editar Perfil")
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "Editar Perfil")
                }
            )
            DropdownMenuItem(
                onClick = { },
                text = {
                    Text("Editar Wallpaper")
                },
                leadingIcon = {
                    Icon(Icons.Filled.Image, contentDescription = "Editar Wallpaper")
                }
            )
        }
    }
}

@SuppressLint("Range")
fun getFileName(context: Context, uri: Uri): String? {
    var result: String? = null
    if (uri.scheme == "content") {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor.use { cursor ->
            if (cursor != null && cursor.moveToFirst()) {
                result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            }
        }
    }
    if (result == null) {
        result = uri.path
        val cut = result?.lastIndexOf('/')
        if (cut != -1) {
            if (cut != null) {
                result = result?.substring(cut + 1)
            }
        }
    }
    return result
}