package com.example.techhub.service

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.techhub.utils.Screen
import com.example.techhub.view.IndexView
import com.example.techhub.view.LoginAuth
import com.example.techhub.view.LoginView
import com.example.techhub.view.PerfilView

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.IndexScreen.route) {
        composable(route = Screen.IndexScreen.route) {
            IndexView(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginView(navController = navController)
        }
        composable(route = Screen.LoginAuthScreen.route) {
            LoginAuth(navController = navController)
        }
        composable(route = Screen.CadastroScreen.route) {
            // CadastroView()
        }
        composable(route = Screen.PerfilViewScreen.route) {
            PerfilView()
        }
    }
}

