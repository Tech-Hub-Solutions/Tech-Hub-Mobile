package com.example.techhub.presentation.explorarTalentos.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import com.example.techhub.presentation.explorarTalentos.ExplorarTalentosViewModel
import com.example.techhub.presentation.explorarTalentos.composables.filtros.FiltroPorAreaETecnologia
import com.example.techhub.presentation.explorarTalentos.composables.filtros.FiltroPorNome
import com.example.techhub.presentation.explorarTalentos.composables.filtros.FiltroPorPreco
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FilterDrawerContent(
    setFiltro: (UsuarioFiltroData) -> Unit,
    viewModel: ExplorarTalentosViewModel,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val context = LocalContext.current
    val talentos = viewModel.talentos.observeAsState().value!!
    val maxPrice = talentos.toList().maxByOrNull { it.precoMedio ?: 0.0 }?.precoMedio ?: 0.0

    val (newFiltro, setNewFiltro) = remember {
        mutableStateOf(
            UsuarioFiltroData(
                tecnologiasIds = mutableListOf(),
                precoMin = 0f,
                precoMax = 10_000.00f
            )
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(0.9f)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = UiText.StringResource(
                R.string.text_filter_by,
            ).asString(context = context),
            color = PrimaryBlue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )

        FiltroPorNome(newFiltro, setNewFiltro, context)

        Spacer(
            modifier = Modifier
                .background(Color(0xFFE0E0E0))
                .fillMaxWidth()
                .height(1.dp)
        )

        FiltroPorAreaETecnologia(newFiltro, setNewFiltro, viewModel, context)

        Spacer(
            modifier = Modifier
                .background(Color(0xFFE0E0E0))
                .fillMaxWidth()
                .height(1.dp)
        )

        FiltroPorPreco(newFiltro, setNewFiltro, maxPrice.toFloat(), context)

        Spacer(
            modifier = Modifier
                .background(Color(0xFFE0E0E0))
                .fillMaxWidth()
                .height(1.dp)
        )

        TextButton(
            onClick = {
                setFiltro(newFiltro)
                scope.launch {
                    drawerState.apply {
                        if (isOpen) close()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryBlue),
            shape = Shapes().extraLarge
        ) {
            Text(
                text = UiText.StringResource(
                    R.string.btn_text_apply_filter,
                ).asString(context = context),
                color = Color.White,
                fontSize = 16.sp
            )
        }

        // Limpar
        OutlinedButton(
            onClick = {
                setNewFiltro(
                    newFiltro.copy(
                        tecnologiasIds = mutableListOf(),
                        precoMin = 0f,
                        precoMax = null,
                    )
                )
                setFiltro(
                    UsuarioFiltroData(
                        tecnologiasIds = mutableListOf(),
                        precoMin = null,
                        precoMax = null,
                    )
                )
                scope.launch {
                    drawerState.apply {
                        if (isOpen) close()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            shape = Shapes().extraLarge,
            border = BorderStroke(1.dp, PrimaryBlue)
        ) {
            Text(
                text = UiText.StringResource(
                    R.string.btn_text_clear_filter,
                ).asString(context = context),
                color = PrimaryBlue,
                fontSize = 16.sp
            )
        }
    }
}
