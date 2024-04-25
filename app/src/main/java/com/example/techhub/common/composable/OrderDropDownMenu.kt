package com.example.techhub.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DensitySmall
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.presentation.ui.theme.GrayTinyButton
import com.example.techhub.presentation.ui.theme.PrimaryBlue

private data class Item(
    val texto:String,
    val valor:String
)

@Composable
fun OrderDropDownMenu(ordem: MutableState<String>) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        Item("Mais avaliado", "avaliacao,desc"),
        Item("Menos avaliado", "avaliacao,asc"),
        Item("Maior preço", "preco,desc"),
        Item("Menor preço", "preco,asc")
    )

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier
                .size(86.dp, 25.dp)
                .background(Color(GrayTinyButton.value))
                .padding(0.dp),
        ) {
            Row(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Ordenar",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Icon(
                    Icons.Filled.DensitySmall,
                    contentDescription = "Localized description",
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp)
                )
            }
        }
        DropdownMenu(
            modifier = Modifier.background(Color(GrayTinyButton.value)),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            item.texto,
                            color = if (ordem.value == item.valor) PrimaryBlue else Color.Black
                        )
                    },
                    onClick = { ordem.value = item.valor; expanded = false }
                )
            }
        }
    }
}