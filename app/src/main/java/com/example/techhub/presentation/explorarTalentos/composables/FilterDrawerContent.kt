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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
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

const val PRECO_MAX = 10_000.00f;

@Composable
fun FilterDrawerContent(
    setFiltro: (UsuarioFiltroData) -> Unit,
    viewModel: ExplorarTalentosViewModel,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val context = LocalContext.current

    val (newFiltro, setNewFiltro) = remember {
        mutableStateOf(
            UsuarioFiltroData(
                tecnologiasIds = mutableListOf(),
                precoMin = 0f,
                precoMax = 5000f
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

        FiltroPorPreco(newFiltro, setNewFiltro, context)

        Spacer(
            modifier = Modifier
                .background(Color(0xFFE0E0E0))
                .fillMaxWidth()
                .height(1.dp)
        )

        ElevatedButton(
            onClick = {
                setFiltro(newFiltro)
                scope.launch {
                    drawerState.apply {
                        if (isOpen) close()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryBlue,
                contentColor = Color.White,
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = UiText.StringResource(
                    R.string.btn_text_apply_filter,
                ).asString(context = context),
                fontSize = 16.sp
            )
        }

        ElevatedButton(
            onClick = {
                setNewFiltro(
                    UsuarioFiltroData(
                        nome = null,
                        tecnologiasIds = mutableListOf(),
                        precoMin = 0f,
                        precoMax = 5000f
                    )
                )
                setFiltro(
                    UsuarioFiltroData(
                        nome = null,
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
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = PrimaryBlue,
            ),
            border = BorderStroke(
                1.dp, Color(PrimaryBlue.value)
            ),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(
                text = UiText.StringResource(
                    R.string.btn_text_clear_filter,
                ).asString(context = context),
                fontSize = 16.sp
            )
        }
    }
}
