package com.example.techhub.common

sealed class Screen(
    val route: String
) {
    data object CadastroGraph : Screen("cadastro_graph")
    data object TravaTelaCadastroView : Screen("trava_tela_cadastro_view")

    data object CadastroFormView : Screen("cadastro_form_view")
    data object CadastroAuthView : Screen("cadastro_auth_view")

    data object LoginGraph : Screen("login_graph")
    data object LoginFormView : Screen("login_form_view")
    data object LoginAuthView : Screen("login_auth_view")

    data object ConfiguracoesUsuarioGraph : Screen("configuracoes_usuario_graph")
    data object ConfiguracoesUsuarioView : Screen("configuracoes_usuario_view")
    data object ConfiguracoesUsuarioFirstAuthView : Screen("configuracoes_usuario_first_auth_view")
}