package com.example.techhub.domain.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.techhub.common.enums.Screen
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.presentation.configUsuario.ConfiguracoesUsuarioViewModel
import com.example.techhub.presentation.configUsuario.composables.ConfiguracoeUsuarioAuth
import com.example.techhub.presentation.configUsuario.composables.ConfiguracoesUsuarioView
import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun NavGraphBuilder.configuracoesUsuarioGraph(
    navController: NavController,
    viewModel: ConfiguracoesUsuarioViewModel = ConfiguracoesUsuarioViewModel()
) {
    val gson: Gson = GsonBuilder().create()

    navigation(
        startDestination = Screen.ConfiguracoesUsuarioView.route,
        route = Screen.ConfiguracoesUsuarioGraph.route
    ) {
        composable(route = Screen.ConfiguracoesUsuarioView.route) {
            ConfiguracoesUsuarioView(
                redirectToAuth = { usuarioSimpleVerifyData ->
                    val user = gson.toJson(usuarioSimpleVerifyData)
                    navController.navigate(Screen.ConfiguracoesUsuarioFirstAuthView.route + "/$user")
                },
                viewModel
            )
        }

        composable(
            route = "${Screen.ConfiguracoesUsuarioFirstAuthView.route}/{usuarioSimpleVerifyData}",
            arguments = listOf(
                navArgument("usuarioSimpleVerifyData") { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val usuarioSimplifyData = navBackStackEntry.arguments?.getString("usuarioSimpleVerifyData")
            val usuarioSimpleVerifyData = gson.fromJson(usuarioSimplifyData, UsuarioSimpleVerifyData::class.java)
            ConfiguracoeUsuarioAuth(
                usuarioSimpleVerifyData = usuarioSimpleVerifyData
            )
        }
    }
}
