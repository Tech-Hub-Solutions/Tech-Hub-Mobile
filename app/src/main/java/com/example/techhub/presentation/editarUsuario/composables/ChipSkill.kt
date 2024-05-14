package com.example.techhub.presentation.editarUsuario.composables

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon

import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.techhub.common.utils.verificarCorFlag
import com.example.techhub.domain.model.flag.FlagData
import com.example.techhub.presentation.ui.theme.GrayText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipSkill(
    flag: FlagData,
    onDismiss: () -> Unit,
) {
    InputChip(
        colors = InputChipDefaults.inputChipColors(
            containerColor = verificarCorFlag(flag.area ?: ""),
            selectedContainerColor = verificarCorFlag(flag.area ?: ""),
        ),
        onClick = {},
        label = { Text(flag.nome ?: "", color = GrayText) },
        selected = true,
        trailingIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.clickable { onDismiss() },
                tint = Color.Gray,
            )
        },
    )
}