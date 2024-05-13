package com.example.techhub.presentation.explorarTalentos.composables.filtros

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.composable.Subtitulo
import com.example.techhub.common.utils.UiText
import com.example.techhub.domain.model.tecnologia.Tecnologia
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import com.example.techhub.presentation.explorarTalentos.ExplorarTalentosViewModel
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltroPorAreaETecnologia(
    newFiltro: UsuarioFiltroData,
    setNewFiltro: (UsuarioFiltroData) -> Unit,
    viewModel: ExplorarTalentosViewModel,
    context: Context
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
        Subtitulo(
            texto = UiText.StringResource(
                R.string.subtitle_tech_area
            ).asString(context = context)
        )

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