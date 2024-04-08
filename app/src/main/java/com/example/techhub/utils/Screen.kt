package com.example.techhub.utils

sealed class Screen(
    val route: String
) {
    data object IndexScreen : Screen("index_screen")
    data object CadastroScreen : Screen("cadastro_screen")
    data object CadastroFreelancerScreen : Screen("cadastro_freelancer_screen")
    data object CadastroEmpresaScreen : Screen("cadastro_empresa_screen")
}