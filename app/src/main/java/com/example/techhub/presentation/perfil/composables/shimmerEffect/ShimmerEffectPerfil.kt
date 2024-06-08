package com.example.techhub.presentation.perfil.composables.shimmerEffect

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.techhub.common.composable.ShimmerEffectCard
import com.example.techhub.common.composable.ShimmerEffectCircle
import com.example.techhub.common.composable.ShimmerEffectText

@Composable
fun ShimmerEffectPerfil(
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,

        ) {
        Spacer(modifier = Modifier.padding(top = 70.dp))
        // Avatar
        ShimmerEffectCircle(size = 120, padding = 20)
        // Nome
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShimmerEffectText(width = 0.4f)
            ShimmerEffectText(width = 0.3f)
        }
        Spacer(modifier = Modifier.padding(top = 24.dp))
        // DescUsuario
        ShimmerEffectText(width = 0.9f, padding = 10)
        ShimmerEffectText(width = 0.8f, padding = 48)
        // Experiência Título
        ShimmerEffectText(width = 0.3f, padding = 16)
        // Experiência
        ShimmerEffectText(width = 0.8f, padding = 10)
        ShimmerEffectText(width = 0.7f, padding = 10)
        ShimmerEffectText(width = 0.9f, padding = 32)
        // Sobre mim Título
        ShimmerEffectText(width = 0.3f, padding = 16)
        // Sobre mim
        ShimmerEffectText(width = 0.6f, padding = 10)
        ShimmerEffectText(width = 0.93f, padding = 10)
        ShimmerEffectText(width = 0.77f, padding = 32)
        // Flags
        ShimmerEffectText(width = 0.2f, padding = 16)
        ShimmerEffectText(width = 0.6f, padding = 10)
        ShimmerEffectText(width = 0.93f, padding = 10)
        ShimmerEffectText(width = 0.85f, padding = 10)
        ShimmerEffectText(width = 0.77f, padding = 32)

        // Avaliações
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            ShimmerEffectText(width = 0.4f)
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
        ShimmerEffectText(width = 0.5f)
        ShimmerEffectText(width = 0.4f)
        }
        // Números das avaliações
        Spacer(modifier = Modifier.padding(top = 24.dp))
        ShimmerEffectText(width = 1f, padding = 10)
        ShimmerEffectText(width = 1f, padding = 10)
        ShimmerEffectText(width = 1f, padding = 10)
        ShimmerEffectText(width = 1f, padding = 10)
        ShimmerEffectText(width = 1f, padding = 10)

        Spacer(modifier = Modifier.padding(top = 80.dp))
    }
}
