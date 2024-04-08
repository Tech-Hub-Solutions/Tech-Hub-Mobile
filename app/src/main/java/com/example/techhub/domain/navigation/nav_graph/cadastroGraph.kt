package com.example.techhub.domain.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.techhub.common.Screen
import com.example.techhub.presentation.cadastro.CadastroFormView
import com.example.techhub.presentation.cadastro.TravaTelaCadastroContent

fun NavGraphBuilder.cadastroGraph(navController: NavController) {
    navigation(
        startDestination = Screen.TravaTelaCadastroScreen.route,
        route = Screen.CadastroGraph.route
    ) {
        composable(route = Screen.TravaTelaCadastroScreen.route) {
            TravaTelaCadastroContent(
                onUserOptionSelected = { userType ->
                    navController.navigate(Screen.CadastroFormScreen.route + "/$userType")
                }
            )
        }

        composable(
            route = "${Screen.CadastroFormScreen.route}/{userType}", arguments = listOf(
                navArgument("userType") { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val userType = navBackStackEntry.arguments?.getString("userType")
            CadastroFormView(navController = navController, userType = userType!!)
        }

    }
}