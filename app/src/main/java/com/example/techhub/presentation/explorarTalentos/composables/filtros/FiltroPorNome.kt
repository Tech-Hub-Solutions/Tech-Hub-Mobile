package com.example.techhub.presentation.explorarTalentos.composables.filtros

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.composable.Subtitulo
import com.example.techhub.common.utils.UiText
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun FiltroPorNome(
    newFiltro: UsuarioFiltroData,
    setNewFiltro: (UsuarioFiltroData) -> Unit,
    context: Context
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Subtitulo(
            texto = UiText.StringResource(
                R.string.subtitle_name
            ).asString(context = context)
        )
        OutlinedTextField(
            value = newFiltro.nome ?: "",
            onValueChange = {
                setNewFiltro(newFiltro.copy(nome = it))
            },
            trailingIcon = {
                Icon(
                    Icons.Outlined.Search,
                    tint = PrimaryBlue,
                    contentDescription = UiText.StringResource(
                        R.string.description_image_search_by
                    ).asString(context = context)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = PrimaryBlue,
            ),
            textStyle = LocalTextStyle.current.copy(
                color = GrayText,
                fontSize = 14.sp
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
    }
}