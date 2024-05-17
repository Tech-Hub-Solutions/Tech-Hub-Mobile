package com.example.techhub.common.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun CircularProgressIndicatorTH(
    size: Double ?= 50.0
) {
    CircularProgressIndicator(
        modifier = Modifier.size(size!!.dp),
        color = PrimaryBlue
    )
}