package com.example.techhub.presentation.explorarTalentos.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import com.example.techhub.presentation.explorarTalentos.ExplorarTalentosViewModel
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class Tecnologia(
    val id: Int,
    val nome: String,
)

@Composable
fun FilterDrawerContent(
    setFiltro: (UsuarioFiltroData) -> Unit,
    viewModel: ExplorarTalentosViewModel,
    drawerState: DrawerState,
    scope: CoroutineScope
) {

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
            text = "Filtrar por:",
            color = PrimaryBlue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )

        FiltroPorNome(newFiltro, setNewFiltro)

        Spacer(
            modifier = Modifier
                .background(Color(0xFFE0E0E0))
                .fillMaxWidth()
                .height(1.dp)
        )

        FiltroPorAreaETecnologia(newFiltro, setNewFiltro, viewModel)

        Spacer(
            modifier = Modifier
                .background(Color(0xFFE0E0E0))
                .fillMaxWidth()
                .height(1.dp)
        )

        FiltroPorPreco(newFiltro, setNewFiltro, maxPrice.toFloat())

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
                text = "Aplicar Filtro",
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
                text = "Limpar Filtro",
                color = PrimaryBlue,
                fontSize = 16.sp
            )
        }

    }
}
