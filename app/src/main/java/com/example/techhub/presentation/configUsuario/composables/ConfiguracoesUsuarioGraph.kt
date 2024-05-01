package com.example.techhub.presentation.configUsuario.composables

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.techhub.common.Screen
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData

fun NavGraphBuilder.configuracoesUsuarioGraph(navController: NavController) {
    val usuarioSimpleVerifyData = MutableLiveData<UsuarioSimpleVerifyData>()

    navigation(
        startDestination = Screen.ConfiguracoesUsuarioView.route,
        route = Screen.ConfiguracoesUsuarioGraph.route
    ) {
        composable(route = Screen.ConfiguracoesUsuarioView.route) {
            ConfiguracoesUsuarioView(
                navController = navController,
                usuarioSimpleVerifyData = usuarioSimpleVerifyData
            )
        }

        composable(
            route = Screen.ConfiguracoesUsuarioFirstAuthView.route,
        ) { navBackStackEntry ->
            ConfiguracoeUsuarioAuth(
                navController = navController,
                usuarioSimpleVerifyData = usuarioSimpleVerifyData.value
            )
        }
    }
}
