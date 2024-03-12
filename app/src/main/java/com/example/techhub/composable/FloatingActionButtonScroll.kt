package com.example.techhub.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.techhub.ui.theme.PrimaryBlue

@Composable
fun FloatingActionButtonScroll(isScrolled: Boolean) {
    if (isScrolled) {
        FloatingActionButton(
            onClick = { /* TODO - ir para o topo da página ao scrollar */ },
            containerColor = Color(PrimaryBlue.value),
            shape = FloatingActionButtonDefaults.smallShape,
            elevation = FloatingActionButtonDefaults.elevation(10.dp),
            contentColor = Color.White,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                Icons.Filled.ArrowUpward,
                "@string/btn_description_up_scroll_to_top",
            )
        }
    }
}