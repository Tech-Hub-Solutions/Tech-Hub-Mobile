package com.example.techhub.common.composable

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.data.prefdatastore.DataStoreManager
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.presentation.configUsuario.ConfiguracoesUsuarioActivity
import com.example.techhub.presentation.login.LoginActivity
import com.example.techhub.presentation.perfil.PerfilActivity
import com.example.techhub.presentation.ui.theme.GrayTinyButton
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

@Composable
fun ConfigDropDownMenu() {
    val context = LocalContext.current
    val actualActivity = context.getActivity()?.javaClass?.simpleName
    val activityExtras = context.getActivity()?.intent?.extras
    val currentId = activityExtras?.getInt("id")

    val dataStoreManager = DataStoreManager(context = context)
    var expanded by remember { mutableStateOf(false) }
    val isOnOwnPerfilActivity =
        actualActivity == "PerfilActivity" && currentId == CurrentUser.userProfile?.id

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .wrapContentSize(Alignment.BottomEnd)
    ) {
        IconButton(
            onClick = { expanded = true },
        ) {
            if (CurrentUser.urlProfileImage.isNullOrEmpty()) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = UiText.StringResource(
                        R.string.btn_description_profile
                    ).asString(context = context),
                    tint = if (isOnOwnPerfilActivity) {
                        PrimaryBlue
                    } else {
                        Color.Gray
                    },
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp)
                )
            } else {
                AsyncImage(
                    model = CurrentUser.urlProfileImage,
                    contentDescription = UiText.StringResource(
                        R.string.btn_description_profile
                    ).asString(context = context),
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp)
                        .border(
                            2.dp,
                            if (isOnOwnPerfilActivity) {
                                PrimaryBlue
                            } else {
                                Color.Gray
                            },
                            CircleShape
                        )
                        .clip(CircleShape)
                        .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
        DropdownMenu(
            modifier = Modifier
                .background(Color(GrayTinyButton.value)),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(
                {
                    DropDownMenuRow(
                        icon = Icons.Filled.Settings,
                        text = UiText.StringResource(
                            R.string.btn_text_configuracoes
                        ).asString(context = context),
                        active = actualActivity == "ConfiguracoesUsuarioActivity",
                        context = context
                    )
                },
                onClick = {
                    expanded = false;
                    startNewActivity(context, ConfiguracoesUsuarioActivity::class.java)
                }
            )
            DropdownMenuItem(
                {
                    DropDownMenuRow(
                        icon = Icons.Filled.Person,
                        text = UiText.StringResource(
                            R.string.btn_text_perfil
                        ).asString(context = context),
                        active = isOnOwnPerfilActivity,
                        context = context
                    )
                },
                onClick = {
                    expanded = false;
                    val extras = Bundle()
                    extras.putInt("id", CurrentUser.userProfile?.id!!)
                    startNewActivity(context, PerfilActivity::class.java, extras)
                }
            )
            Divider(
                modifier = Modifier
                    .padding(horizontal = 6.dp),
                color = Color.Gray.copy(alpha = 0.2f)
            )
            DropdownMenuItem(
                {
                    DropDownMenuRow(
                        icon = Icons.Filled.Logout,
                        text = UiText.StringResource(
                            R.string.btn_text_sair
                        ).asString(context = context),
                        context = context
                    )
                },
                onClick = {
                    expanded = false
                    CoroutineScope(Dispatchers.IO).launch {
                        dataStoreManager.clearDataStore()
                        startNewActivity(context, LoginActivity::class.java)
                    }
                }
            )
        }
    }
}

@Composable
fun DropDownMenuRow(
    icon: ImageVector,
    text: String,
    active: Boolean = false,
    context: Context
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val color = if (active) {
            PrimaryBlue
        } else {
            Color(0xFF858585)
        }
        Icon(
            icon,
            contentDescription = UiText.StringResource(
                R.string.btn_description_profile
            ).asString(context = context),
            tint = color,
            modifier = Modifier
                .width(28.dp)
                .height(28.dp)
                .padding(end = 8.dp)
        )
        Text(text = text, color = color)
    }
}