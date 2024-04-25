package com.example.techhub.presentation.explorarTalentos.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import com.example.techhub.presentation.explorarTalentos.ExplorarTalentosViewModel
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import androidx.transition.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltroPorNome(newFiltro: UsuarioFiltroData, setNewFiltro: (UsuarioFiltroData) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Subtitulo(texto = "Nome")
        OutlinedTextField(
            value = newFiltro.nome ?: "",
            onValueChange = {
                setNewFiltro(newFiltro.copy(nome = it))
            },
            trailingIcon = {
               Icon(
                   Icons.Outlined.Search,
                   tint = PrimaryBlue,
                   contentDescription = "Pesquisar por nome"
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltroPorAreaETecnologia(
    newFiltro: UsuarioFiltroData,
    setNewFiltro: (UsuarioFiltroData) -> Unit,
    viewModel: ExplorarTalentosViewModel
) {
    val tecnologias = remember { mutableStateMapOf<String, MutableList<Tecnologia>>() }
    var expanded by remember { mutableStateOf(false) }
    val flags = viewModel.flags.observeAsState().value!!


    LaunchedEffect(flags) {
        if (flags.size > 0) {
            setNewFiltro(newFiltro.copy(area = flags[0].area!!))
        }
        flags.forEach {
            val area = it.area;
            val id = it.id;
            val nome = it.nome;

            if (area == "Soft-skills") return@forEach

            if (tecnologias.containsKey(area)) {
                val list = tecnologias[area];
                if (tecnologias[area]?.contains(
                        Tecnologia(
                            id!!,
                            nome!!
                        )
                    ) == false
                ) list?.add(Tecnologia(id!!, nome!!))
            } else {
                tecnologias[area!!] = mutableListOf(Tecnologia(id!!, nome!!))
            }
        }
    }


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Subtitulo(texto = "Área de Tecnologia")

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                readOnly = true,
                value = newFiltro.area ?: "",
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = PrimaryBlue,
                ),
                textStyle = LocalTextStyle.current.copy(
                    color = GrayText,
                    fontSize = 14.sp
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                tecnologias.keys.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it) },
                        onClick = {
                            setNewFiltro(
                                newFiltro.copy(
                                    area = it,
                                    tecnologiasIds = mutableListOf()
                                )
                            )
                            expanded = false
                        }
                    )
                }
            }
        }

        tecnologias.filter { it.key == (newFiltro.area ?: "") }
            .forEach {
                it.value.forEach {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            colors = CheckboxDefaults.colors(
                                checkmarkColor = Color.White,
                                checkedColor = PrimaryBlue
                            ),

                            checked = newFiltro.tecnologiasIds!!.contains(it.id),
                            onCheckedChange = { isChecked ->
                                val tecnologiasSelected = newFiltro.tecnologiasIds.toMutableList()
                                if (isChecked) {
                                    tecnologiasSelected.add(it.id)
                                } else {
                                    tecnologiasSelected.remove(it.id)
                                }
                                setNewFiltro(newFiltro.copy(tecnologiasIds = tecnologiasSelected))
                            }
                        )
                        Text(text = it.nome, color = GrayText, fontSize = 14.sp)
                    }
                }
            }
    }
}


@Composable
fun FiltroPorPreco(
    newFiltro: UsuarioFiltroData,
    setNewFiltro: (UsuarioFiltroData) -> Unit,
    maxPrice: Float
) {
    var sliderPosition by remember { mutableStateOf(0f..maxPrice) }

    LaunchedEffect(maxPrice) {
        sliderPosition = sliderPosition.start..maxPrice
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Subtitulo(texto = "Preço")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                label = { Text(text = "Mínimo") },
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
                label = { Text(text = "Máximo") },
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


@Composable
fun Subtitulo(texto: String) {
    Text(
        text = texto,
        color = GrayText,
        fontSize = 15.sp,
        fontWeight = FontWeight.Black,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}