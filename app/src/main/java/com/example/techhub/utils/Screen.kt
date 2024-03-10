package com.example.techhub.utils

sealed class Screen(
    val route: String
) {
    data object IndexScreen : Screen("index_screen")
    data object CadastroScreen : Screen("cadastro_screen")
    data object LoginScreen : Screen("login_screen")
}