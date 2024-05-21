package com.example.techhub.common.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.techhub.common.utils.shimmerEffect

@Composable
fun ShimmerEffectCard(size: Int, padding: Int? = 0) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .shimmerEffect()
    )
    Spacer(modifier = Modifier.padding(top = padding!!.dp))
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