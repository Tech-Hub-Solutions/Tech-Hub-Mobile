package com.example.techhub.presentation.perfil.composables.images

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun RoundedPerfilImageIcon(context: Context) {
    Icon(
        Icons.Filled.Person,
        contentDescription = UiText.StringResource(
            R.string.btn_description_profile
        ).asString(context = context),
        tint = PrimaryBlue,
        modifier = Modifier
            .size(113.dp)
            .clip(CircleShape)
            .background(Color(0xFFE4E4E4))
            .padding(2.dp)
            .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
    )
}