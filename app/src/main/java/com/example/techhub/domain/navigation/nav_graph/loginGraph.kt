package com.example.techhub.domain.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.techhub.common.enums.Screen
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.presentation.login.composables.LoginAuthView
import com.example.techhub.presentation.login.composables.LoginFormView
import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun NavGraphBuilder.loginGraph(navController: NavController) {
    val gson: Gson = GsonBuilder().create()

    navigation(
        startDestination = Screen.LoginFormView.route,
        route = Screen.LoginGraph.route
    ) {
        composable(route = Screen.LoginFormView.route) {
            LoginFormView { userData ->
                val userJson = gson.toJson(userData)
                navController.navigate(Screen.LoginAuthView.route + "/$userJson")
            }
        }

        composable(
            route = "${Screen.LoginAuthView.route}/{userJson}",
            arguments = listOf(
                navArgument("userJson") { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val userJson = navBackStackEntry.arguments?.getString("userJson")
            val usuarioVerifyData =
                gson.fromJson(userJson, UsuarioLoginData::class.java)

            LoginAuthView(
                usuarioVerifyData = usuarioVerifyData
            )
        }
    }
}