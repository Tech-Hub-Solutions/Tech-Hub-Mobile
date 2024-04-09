package com.example.techhub.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DensitySmall
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.ui.theme.GrayButtonText
import com.example.techhub.ui.theme.GrayStar
import com.example.techhub.ui.theme.GrayTinyButton

@Composable
fun OrderDropDownMenu(ordem: MutableState<String>){
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier
                .size(80.dp,25.dp)
                .background(Color(GrayTinyButton.value))
                .padding(0.dp),
        ) {
            Row (modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround){
                Text(text = "Ordenar",
                    fontSize = 12.sp)
                Icon(Icons.Filled.DensitySmall,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(15.dp)
                )
            }
        }
        DropdownMenu(
            modifier = Modifier.background(Color(GrayTinyButton.value)),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(
                text = { Text("Mais avaliado") },
                onClick = { ordem.value = "avaliacao,desc"}
            )
            DropdownMenuItem(
                text = { Text("Menos avaliado") },
                onClick = { ordem.value = "avaliacao,asc"}
            )
            DropdownMenuItem(
                text = { Text("Maior preço") },
                onClick = { ordem.value = "preco,desc"}
            )
            DropdownMenuItem(
                text = { Text("Menor preço") },
                onClick = { ordem.value = "preco,asc"}
            )
        }
    }
}