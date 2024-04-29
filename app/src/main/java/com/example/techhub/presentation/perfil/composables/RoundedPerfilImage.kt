package com.example.techhub.presentation.perfil.composables

import android.content.Context
import android.security.identity.AccessControlProfile
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        } else {
            if (imagePath.isNullOrBlank()) {
                RoundedPerfilImageIcon()
            } else {
                AsyncImage(
                    model = imagePath,
                    contentDescription = "Rounded Perfil Image",
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
            Icon(
                Icons.Outlined.Edit,
                contentDescription = "Edit Perfil Image",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(PrimaryBlue)
                    .align(Alignment.BottomEnd)
                    .padding(5.dp)
                    .clickable {
                        expanded.value = !expanded.value
                    }

            )
            MenuEditarImagens(
                expanded,
                Modifier.align(Alignment.BottomCenter),
                perfilViewModel,
                context
            )
        }
    }
}


@Composable
fun RoundedPerfilImageIcon() {
    Icon(
        Icons.Filled.Person,
        contentDescription = "@string/btn_description_profile",
        tint = PrimaryBlue,
        modifier = Modifier
            .size(115.dp)
            .clip(CircleShape)
            .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
    )
}