package com.example.techhub.common.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.techhub.common.utils.shimmerEffect

@Composable
fun ShimmerEffectCard(modifier: Modifier) {
    Box(
        modifier = Modifier
            .heightIn(200.dp, 220.dp)
            .then(modifier)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ShimmerEffectText(width = 0.4f, padding = 16)
            ShimmerEffectText(width = 0.8f, padding = 8)
            ShimmerEffectText(width = 0.67f, padding = 8)
        }
    }
}


@Composable
fun ShimmerEffectText(width: Float, padding: Int? = 0) {
    Box(
        modifier = Modifier
            .height(20.dp)
            .fillMaxWidth(width)
            .shimmerEffect()
    )
    Spacer(modifier = Modifier.padding(top = padding!!.dp))
}

@Composable
fun ShimmerEffectCircle(size: Int, padding: Int? = 0) {
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(size.dp)
            .shimmerEffect()
    )
    Spacer(modifier = Modifier.padding(top = padding!!.dp))
}