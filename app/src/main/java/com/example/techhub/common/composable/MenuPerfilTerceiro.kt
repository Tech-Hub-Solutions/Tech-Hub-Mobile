package com.example.techhub.common.composable

import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Upload
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
import com.example.techhub.presentation.perfil.PerfilViewModel

@Composable
fun MenuPerfilTerceiro(
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
                            "Currículo de ${userName}"
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
                        val toastErrorMessage =
                            "Link de perfil copiado para área de transferência!"
                        (context as Activity).runOnUiThread {
                            showToastError(context = context, message = toastErrorMessage)
                        }
                },
                text = {
                    Text("Compartilhar perfil")
                },
                leadingIcon = {
                    Icon(Icons.Filled.Link, contentDescription = "Compartilhar perfil")
                }
            )
        }
    }
}