package com.example.techhub.composable

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.techhub.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    willRedirectToActivity: Boolean,
    activity: Class<*>? = null,
    context: Context? = null,
    navController: NavController? = null,
    route: String? = null,
    title: String
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier.padding(bottom = 8.dp),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier.background(Color.Transparent),
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                if (willRedirectToActivity) {
                    StartNewActivity(context!!, activity!!)
                } else {
                    navController!!.navigate(route!!)
                }

            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = "botão de retornar ao início",
                    tint = Color.Black
                )
            }
        }
    )
}
