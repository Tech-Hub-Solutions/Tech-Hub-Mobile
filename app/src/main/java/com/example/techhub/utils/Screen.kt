package com.example.techhub.utils

sealed class Screen(
    val route: String
) {
    data object IndexScreen : Screen("index_screen")
    data object CadastroGraph : Screen("cadastro")
    data object TravaTelaCadastroScreen : Screen("trava_tela_cadastro_screen")

    // TODO - Substituir o Freelancer/Empresa pelo CadastroContent
    data object CadastroContent : Screen("trava_tela_cadastro_screen")
    data object CadastroFreelancerScreen : Screen("cadastro_freelancer_screen")
    data object CadastroEmpresaScreen : Screen("cadastro_empresa_screen")
    data object LoginGraph : Screen("login_screen")
    data object LoginContent : Screen("login_content")
    data object LoginAuthScreen : Screen("login_auth_screen")
}