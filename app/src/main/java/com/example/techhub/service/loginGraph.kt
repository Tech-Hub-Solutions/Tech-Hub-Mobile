package com.example.techhub.service

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.techhub.utils.Screen
import com.example.techhub.view.LoginAuthContent
import com.example.techhub.view.LoginFormView

fun NavGraphBuilder.loginGraph(navController: NavController) {
    navigation(
        startDestination = Screen.LoginFormScreen.route,
        route = Screen.LoginGraph.route
    ) {
        composable(route = Screen.LoginFormScreen.route) {
            LoginFormView(
                onLoginAuth = {
                    navController.navigate(Screen.LoginAuthScreen.route)
                }
            )
        }

        composable(route = Screen.LoginAuthScreen.route) {
            LoginAuthContent()
        }
    }
}