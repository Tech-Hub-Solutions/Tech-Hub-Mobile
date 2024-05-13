package com.example.techhub.presentation.explorarTalentos.composables.filtros

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.composable.Subtitulo
import com.example.techhub.common.utils.UiText
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun FiltroPorPreco(
    newFiltro: UsuarioFiltroData,
    setNewFiltro: (UsuarioFiltroData) -> Unit,
    maxPrice: Float,
    context: Context
) {
    var sliderPosition by remember { mutableStateOf(0f..maxPrice) }

    LaunchedEffect(maxPrice) {
        sliderPosition = sliderPosition.start..maxPrice
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Subtitulo(
            texto = UiText.StringResource(
                R.string.subtitle_price
            ).asString(context = context)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                label = {
                    Text(
                        text = UiText.StringResource(
                            R.string.label_minimum
                        ).asString(context = context)
                    )
                },
                value = "R$ ${String.format("%.2f", sliderPosition.start)}",
                onValueChange = {
                    val cleanValue = it.removePrefix("R$ ")

                    var floatValue = try {
                        cleanValue.toFloat()
                    } catch (e: Exception) {
                        sliderPosition.start
                    }

                    if (floatValue < 0) {
                        floatValue = 0f
                    }

                    setNewFiltro(
                        newFiltro.copy(
                            precoMin = floatValue
                        )
                    )

                    sliderPosition = floatValue..sliderPosition.endInclusive
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 8.dp)
            )

            OutlinedTextField(
                label = {
                    Text(
                        text = UiText.StringResource(
                            R.string.label_maximum
                        ).asString(context = context)
                    )
                },
                value = "R$ ${String.format("%.2f", sliderPosition.endInclusive)}",
                onValueChange = {
                    val cleanValue = it.removePrefix("R$ ")

                    var floatValue = try {
                        cleanValue.toFloat()
                    } catch (e: Exception) {
                        sliderPosition.endInclusive
                    }

                    if (floatValue > 10_000.00f) {
                        floatValue = 10_000.00f
                    }

                    setNewFiltro(
                        newFiltro.copy(
                            precoMax = floatValue
                        )
                    )

                    sliderPosition = sliderPosition.start..floatValue
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 8.dp)
            )

        }
        RangeSlider(
            value = sliderPosition,
            onValueChange = { range -> sliderPosition = range },
            valueRange = 0f..10_000.00f,
            onValueChangeFinished = {
                setNewFiltro(
                    newFiltro.copy(
                        precoMin = sliderPosition.start,
                        precoMax = sliderPosition.endInclusive
                    )
                )
            },
            colors = SliderDefaults.colors(PrimaryBlue)
        )
    }
}