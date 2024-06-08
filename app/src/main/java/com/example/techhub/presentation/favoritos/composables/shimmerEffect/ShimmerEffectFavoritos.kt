package com.example.techhub.presentation.favoritos.composables.shimmerEffect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techhub.common.composable.ShimmerEffectCard

@Composable
fun ShimmerEffectFavoritos() {

    Column(
        modifier = Modifier
            .padding(0.dp)
            .background(Color.White)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,

        ) {
        Spacer(modifier = Modifier.padding(top = 12.dp))
        // Cards
        for (i in 0..4) {
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                ShimmerEffectCard(modifier = Modifier.weight(1f))
                ShimmerEffectCard(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.padding(top = 24.dp))
        }
    }
}