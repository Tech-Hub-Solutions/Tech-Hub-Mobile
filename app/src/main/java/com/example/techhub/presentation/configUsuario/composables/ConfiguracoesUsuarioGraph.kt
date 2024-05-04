package com.example.techhub.presentation.configUsuario.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.techhub.common.Screen
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.presentation.configUsuario.ConfiguracoesUsuarioViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


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
                navController = navController,
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
                navController = navController,
                usuarioSimpleVerifyData = usuarioSimpleVerifyData
            )
        }
    }
}
