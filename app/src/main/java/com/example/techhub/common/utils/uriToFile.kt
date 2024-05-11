package com.example.techhub.common.utils

import android.content.Context
import android.net.Uri
import com.example.techhub.presentation.perfil.composables.getFileName
import java.io.File
import java.io.FileOutputStream

fun uriToFile(
    context: Context,
    uri: Uri
): File? {
    val inputStream = context.contentResolver.openInputStream(uri)
    val file = getFileName(context, uri)?.let { File(context.cacheDir, it) }
    val outputStream = FileOutputStream(file)
    inputStream.use { input ->
        outputStream.use { output ->
            input!!.copyTo(output)
        }
    }
    return file;
}