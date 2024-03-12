package com.example.techhub.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.techhub.composable.BottomBar
import com.example.techhub.composable.FloatingActionButtonScroll

@Composable
fun PerfilView() {
    Scaffold(
        bottomBar = {
            BottomBar(isEmpresa = true)
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            FloatingActionButtonScroll(isScrolled = true)
        }

    }
}