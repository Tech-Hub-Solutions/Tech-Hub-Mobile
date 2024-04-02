package com.example.techhub.service

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.techhub.common.Screen
import com.example.techhub.presentation.view.CadastroEmpresaView
import com.example.techhub.presentation.view.CadastroFreelancerView
import com.example.techhub.presentation.view.IndexView
import com.example.techhub.presentation.view.LoginAuth
import com.example.techhub.presentation.login.LoginScreen
import com.example.techhub.presentation.view.CadastroView

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.IndexScreen.route) {
        composable(route = Screen.IndexScreen.route) {
            IndexView(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.LoginAuthScreen.route) {
            LoginAuth(navController = navController)
        }
        composable(route = Screen.CadastroScreen.route) {
            CadastroView(navController = navController)
        }
        composable(route = Screen.CadastroFreelancerScreen.route) {
            CadastroFreelancerView(navController = navController)
        }
        composable(route = Screen.CadastroEmpresaScreen.route) {
            CadastroEmpresaView(navController = navController)
        }
    }
}

