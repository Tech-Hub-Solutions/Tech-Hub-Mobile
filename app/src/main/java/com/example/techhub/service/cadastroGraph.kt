package com.example.techhub.service

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.techhub.utils.Screen
import com.example.techhub.view.CadastroEmpresaView
import com.example.techhub.view.CadastroFreelancerView
import com.example.techhub.view.TravaTelaCadastroContent

fun NavGraphBuilder.cadastroGraph(navController: NavController) {
    navigation(
        startDestination = Screen.TravaTelaCadastroScreen.route,
        route = Screen.CadastroGraph.route
    ) {
        composable(route = Screen.TravaTelaCadastroScreen.route) {
            TravaTelaCadastroContent(
                navController = navController,
                onUserOptionSelected = {
                    // TODO - Add lógica p/ redirecionamento Cadastro único sem Freelancer/Empresa
                    navController.navigate(Screen.CadastroFreelancerScreen.route)
                }
            )
        }

        composable(route = Screen.CadastroFreelancerScreen.route) {
            CadastroFreelancerView(navController = navController)
        }

        composable(route = Screen.CadastroEmpresaScreen.route) {
            CadastroEmpresaView(navController = navController)
        }

    }
}
