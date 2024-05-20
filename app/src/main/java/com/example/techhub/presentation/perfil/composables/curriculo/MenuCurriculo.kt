package com.example.techhub.presentation.perfil.composables.curriculo

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Upload
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
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.techhub.R
import com.example.techhub.common.enums.TipoArquivo
import com.example.techhub.common.utils.UiText
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
    clipboardManager: ClipboardManager
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
                        val toastErrorMessage = UiText.StringResource(
                            R.string.toast_error_sem_curriculo
                        ).asString(context = context);

                        (context as Activity).runOnUiThread {
                            showToastError(context = context, message = toastErrorMessage)
                        }
                    } else {
                        perfilViewModel.downloadFile(
                            context,
                            urlCurriculo,
                            UiText.StringResource(
                                R.string.text_curriculum_name
                            ).asString(context = context) + userName
                        )
                        expanded.value = !expanded.value
                    }
                },
                text = {
                    Text(
                        UiText.StringResource(
                            R.string.btn_download_curriculo
                        ).asString(context = context)
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Download,
                        contentDescription = UiText.StringResource(
                            R.string.btn_download_curriculo
                        ).asString(context = context)
                    )
                }
            )
            DropdownMenuItem(
                onClick = {
                    tipoArquivo.value = TipoArquivo.CURRICULO
                    getContent.launch("application/pdf")
                },
                text = {
                    Text(
                        UiText.StringResource(
                            R.string.btn_upload_curriculo
                        ).asString(context = context)
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Upload,
                        contentDescription = UiText.StringResource(
                            R.string.btn_upload_curriculo
                        ).asString(context = context)
                    )
                }
            )
            DropdownMenuItem(
                onClick = {
                    clipboardManager.setText(AnnotatedString(("BANANA")))

                    Log.d("CLIPBOARD", clipboardManager.getText()?.text?.let {
                        it
                    }.toString())

                    val toastErrorMessage =
                        UiText.StringResource(
                            R.string.toast_text_link_perfil
                        ).asString(context = context)

                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                },
                text = {
                    Text(UiText.StringResource(
                        R.string.btn_share_perfi
                    ).asString(context = context))
                },
                leadingIcon = {
                    Icon(Icons.Filled.Link,
                        contentDescription = UiText.StringResource(
                            R.string.btn_share_perfi
                        ).asString(context = context))
                }
            )
        }
    }

}

