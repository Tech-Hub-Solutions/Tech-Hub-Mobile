package com.example.techhub.utils

sealed class Screen(
    val route: String
) {
    data object IndexScreen : Screen("index_screen")
    data object CadastroGraph : Screen("cadastro_graph")
    data object TravaTelaCadastroScreen : Screen("trava_tela_cadastro_screen")
    data object CadastroFormScreen : Screen("cadastro_form_screen")
    data object LoginGraph : Screen("login_graph")
    data object LoginFormScreen : Screen("login_form_screen")
    data object LoginAuthScreen : Screen("login_auth_screen")
}