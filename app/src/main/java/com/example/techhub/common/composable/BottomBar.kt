package com.example.techhub.common.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.TravelExplore
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.shadowCustom
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.presentation.explorarTalentos.ExplorarTalentosActivity
import com.example.techhub.presentation.favoritos.FavoritosActivity
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun BottomBar() {
    val context = LocalContext.current
    val actualActivity = context.javaClass.simpleName
    val isUserEmpresa = CurrentUser.isEmpresa

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadowCustom(
                color = Color.Black.copy(alpha = 0.4f),
                offsetY = 4.dp,
                blurRadius = 16.dp,
                shapeRadius = 16.dp
            ),
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly,

                ) {
                IconButton(onClick = {
                    startNewActivity(context, ExplorarTalentosActivity::class.java)
                }) {
                    Icon(
                        Icons.Outlined.TravelExplore,
                        contentDescription = UiText.StringResource(
                            R.string.btn_description_search_talents
                        ).asString(context = context),
                        tint = if (actualActivity == "ExplorarTalentosActivity") {
                            PrimaryBlue
                        } else {
                            Color.Gray
                        },
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp)
                    )
                }

                if (isUserEmpresa) {
                    IconButton(onClick = {
                        startNewActivity(context, FavoritosActivity::class.java)
                    }) {
                        Icon(
                            Icons.Filled.FavoriteBorder,
                            contentDescription = UiText.StringResource(
                                R.string.btn_description_favorites
                            ).asString(context = context),
                            tint = if (actualActivity == "FavoritosActivity") {
                                PrimaryBlue
                            } else {
                                Color.Gray
                            },
                            modifier = Modifier
                                .width(28.dp)
                                .height(28.dp)
                        )
                    }
                }

                ConfigDropDownMenu()
            }
        },

        containerColor = Color.White,
    )
}

