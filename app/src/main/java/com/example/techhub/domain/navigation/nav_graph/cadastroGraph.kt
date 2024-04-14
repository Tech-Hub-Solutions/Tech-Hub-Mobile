package com.example.techhub.domain.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.techhub.common.Screen
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.presentation.cadastro.composables.CadastroAuthView
import com.example.techhub.presentation.cadastro.composables.CadastroFormView
import com.example.techhub.presentation.cadastro.composables.TravaTelaCadastroView
import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun NavGraphBuilder.cadastroGraph(navController: NavController) {
    val gson: Gson = GsonBuilder().create()

    navigation(
        startDestination = Screen.TravaTelaCadastroView.route,
        route = Screen.CadastroGraph.route
    ) {
        composable(route = Screen.TravaTelaCadastroView.route) {
            TravaTelaCadastroView(
                onUserOptionSelected = { userType ->
                    navController.navigate(Screen.CadastroFormView.route + "/$userType")
                }
            )
        }

        composable(
            route = "${Screen.CadastroFormView.route}/{userType}",
            arguments = listOf(
                navArgument("userType") { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val userType = navBackStackEntry.arguments?.getString("userType")
            CadastroFormView(
                navController = navController,
                userType = userType!!,
                onSuccess = { usuarioData ->
                    val userJson = gson.toJson(usuarioData)
                    navController.navigate(Screen.CadastroAuthView.route + "/$userJson")
                }
            )
        }

        composable(
            route = "${Screen.CadastroAuthView.route}/{userJson}",
            arguments = listOf(
                navArgument("userJson") { type = NavType.StringType },
            )
        ) { navBackStackEntry ->
            val userJson = navBackStackEntry.arguments?.getString("userJson")
            val usuarioSimpleVerifyData =
                gson.fromJson(userJson, UsuarioSimpleVerifyData::class.java)

            CadastroAuthView(
                navController = navController,
                usuarioSimpleVerifyData = usuarioSimpleVerifyData
            )
        }
    }
}
