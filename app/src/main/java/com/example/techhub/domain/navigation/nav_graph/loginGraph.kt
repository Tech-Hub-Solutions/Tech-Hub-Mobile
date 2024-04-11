package com.example.techhub.domain.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.techhub.common.Screen
import com.example.techhub.presentation.login.components.LoginAuthView
import com.example.techhub.presentation.login.components.LoginFormView

fun NavGraphBuilder.loginGraph(navController: NavController) {
    navigation(
        startDestination = Screen.LoginFormView.route,
        route = Screen.LoginGraph.route
    ) {
        composable(route = Screen.LoginFormView.route) {
            LoginFormView(
                onLoginAuth = {
                    navController.navigate(Screen.LoginAuthView.route)
                }
            )
        }

        composable(route = Screen.LoginAuthView.route) {
            LoginAuthView()
        }
    }
}