package com.example.techhub.presentation.favoritos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.techhub.common.Screen
import com.example.techhub.composable.SetBarColor
import com.example.techhub.presentation.favoritos.composables.FavoritosView
import com.example.techhub.presentation.ui.theme.TechHubTheme

class FavoritosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TechHubTheme {
                SetBarColor(color = Color.White)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.FavoritosView.route,
                        route = Screen.FavoritosView.route
                    ) {
                        composable(route = Screen.FavoritosView.route) {
                            FavoritosView()
                        }
                    }
                }
            }
        }
    }
}