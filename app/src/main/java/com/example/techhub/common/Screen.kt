package com.example.techhub.common

sealed class Screen(
    val route: String
) {
    data object IndexScreen : Screen("index_screen")
    data object CadastroScreen : Screen("cadastro_screen")
    data object CadastroFreelancerScreen : Screen("cadastro_freelancer_screen")
    data object CadastroEmpresaScreen : Screen("cadastro_empresa_screen")
    data object LoginScreen : Screen("login_screen")
    data object LoginAuthScreen : Screen("login_auth_screen")
}