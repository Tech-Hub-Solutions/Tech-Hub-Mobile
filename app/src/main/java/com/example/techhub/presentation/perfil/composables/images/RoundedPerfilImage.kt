package com.example.techhub.presentation.perfil.composables.images

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.techhub.R
import com.example.techhub.common.composable.CircularProgressIndicatorTH
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.shadowCustom
import com.example.techhub.presentation.perfil.PerfilViewModel
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun RoundedPerfilImage(
    imagePath: String?,
    isOwnProfile: Boolean,
    perfilViewModel: PerfilViewModel,
    context: Context,
    isLoading: Boolean
) {
    Box {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .size(115.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .fillMaxWidth()
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicatorTH()
            }
        } else {
            if (imagePath.isNullOrBlank()) {
                RoundedPerfilImageIcon(context = context)
            } else {
                AsyncImage(
                    model = imagePath,
                    contentDescription = UiText.StringResource(
                        R.string.description_image_rounded_perfil
                    ).asString(context = context),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(115.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
                )
            }
        }

        if (isOwnProfile) {
            val expanded = remember { mutableStateOf(false) }
            IconButton(
                onClick = { expanded.value = !expanded.value },
                modifier = Modifier
                    .shadowCustom(
                        blurRadius = 4.dp,
                        shapeRadius = 100.dp,
                        color = Color.Black.copy(alpha = 0.5f)
                    )
                    .clip(CircleShape)
                    .background(Color.White)
                    .align(Alignment.BottomEnd)

            ) {
                Icon(
                    Icons.Rounded.Edit,
                    contentDescription = UiText.StringResource(
                        R.string.description_image_edit_perfil
                    ).asString(context = context),
                    tint = PrimaryBlue,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                )
            }

            MenuEditarImagens(
                expanded,
                Modifier.align(Alignment.BottomCenter),
                perfilViewModel,
                context
            )
        }
    }
}