package com.example.techhub.presentation.favoritos.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.presentation.comparar.composables.CompararTalentosView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetCompararTalentos(
    selectedUsers: SnapshotStateList<UsuarioFavoritoData>,
    isAbleToCompare: MutableState<Boolean>
) {
    var scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    if (isAbleToCompare.value == false && selectedUsers.isNotEmpty()) {
        selectedUsers.clear()
    } else {
        if (selectedUsers.size == 2) {
            BottomSheetScaffold(
                modifier = Modifier
                    .fillMaxHeight(.98f)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 56.dp),
                scaffoldState = scaffoldState,
                sheetContainerColor = Color.White,
                sheetContent = {
                    CompararTalentosView(selectedUsers)
                },
                sheetPeekHeight = 70.dp,
                sheetDragHandle = {
                    TextButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(70.dp)
                            .background(color = Color(0xFF0F9EEA))
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Comparar Selecionados",
                                color = Color.White,
                                fontSize = 24.sp
                            )
                            Icon(
                                imageVector = Icons.Filled.CompareArrows,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                },
                sheetShape = MaterialTheme.shapes.extraSmall
            ) {

            }
        }
    }
}