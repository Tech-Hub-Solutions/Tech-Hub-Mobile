package com.example.techhub.service

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.techhub.IndexContent
import com.example.techhub.utils.Screen
import com.example.techhub.view.CadastroEmpresaView
import com.example.techhub.view.CadastroFreelancerView
import com.example.techhub.view.LoginAuth

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.IndexScreen.route) {
        composable(route = Screen.IndexScreen.route) {
            IndexContent()
        }
        composable(route = Screen.LoginAuthScreen.route) {
            LoginAuth(navController = navController)
        }
        composable(route = Screen.CadastroFreelancerScreen.route) {
            CadastroFreelancerView(navController = navController)
        }
        composable(route = Screen.CadastroEmpresaScreen.route) {
            CadastroEmpresaView(navController = navController)
        }
    }
}

