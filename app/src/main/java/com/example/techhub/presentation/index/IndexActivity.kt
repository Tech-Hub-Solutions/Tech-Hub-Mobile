package com.example.techhub.presentation.index

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.techhub.common.Screen
import com.example.techhub.common.utils.rememberWindowInfo
import com.example.techhub.composable.SetBarColor
import com.example.techhub.presentation.index.composables.IndexView
import com.example.techhub.presentation.index.utils.showWelcomeToast
import com.example.techhub.presentation.ui.theme.TechHubTheme

private var wasToastShowed: Boolean? = false

class IndexActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechHubTheme(
                activity = this,
                content = {
                    SetBarColor(color = Color.White)

                    Surface(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp).fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = Screen.IndexView.route,
                            route = "root_route"
                        ) {
                            composable(route = Screen.IndexView.route) {
                                IndexView()
                            }
                        }
                    }
                })

            wasToastShowed =
                wasToastShowed?.let { showWelcomeToast(context = this, wasToastShowed = it) }
        }
    }
}