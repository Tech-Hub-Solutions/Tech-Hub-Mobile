package com.example.techhub.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.presentation.configUsuario.ConfiguracoesUsuarioActivity
import com.example.techhub.presentation.perfil.PerfilActivity
import com.example.techhub.presentation.ui.theme.GrayTinyButton
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun ConfigDropDownMenu() {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .wrapContentSize(Alignment.BottomEnd)
    ) {
        IconButton(
            onClick = { expanded = true },
        ) {
            Icon(
                Icons.Filled.Person,
                contentDescription = "@string/btn_description_profile",
                tint = Color(PrimaryBlue.value),
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
            )
        }
        DropdownMenu(
            modifier = Modifier
                .background(Color(GrayTinyButton.value)),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(
                { DropDownMenuRow(icon = Icons.Filled.Settings, text = "Configurações") },
                onClick = { expanded = false;
                    startNewActivity(context, ConfiguracoesUsuarioActivity::class.java)}
            )
            DropdownMenuItem(
                { DropDownMenuRow(icon = Icons.Filled.Person, text = "Perfil") },
                onClick = { expanded = false;
                    startNewActivity(context, PerfilActivity::class.java) }
            )
            Divider(modifier = Modifier
                .padding(horizontal = 6.dp),
                color = Color.Gray.copy(alpha = 0.2f))
            DropdownMenuItem(
                { DropDownMenuRow(icon = Icons.Filled.Logout, text = "Sair") },
                onClick = { expanded = false }
            )
        }
    }
}

@Composable
fun DropDownMenuRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            icon,
            contentDescription = "@string/btn_description_profile",
            tint = Color(0xFF858585),
            modifier = Modifier
                .width(28.dp)
                .height(28.dp)
                .padding(end = 8.dp)
        )
        Text(text = text, color = Color(0xFF858585))
    }
}