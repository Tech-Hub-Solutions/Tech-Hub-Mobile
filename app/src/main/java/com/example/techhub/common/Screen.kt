package com.example.techhub.common

sealed class Screen(
    val route: String
) {
    data object IndexView : Screen("index_view")

    data object CadastroGraph : Screen("cadastro_graph")
    data object TravaTelaCadastroView : Screen("trava_tela_cadastro_view")

    data object CadastroFormView : Screen("cadastro_form_view")
    data object CadastroAuthView : Screen("cadastro_auth_view")

    data object LoginGraph : Screen("login_graph")
    data object LoginFormView : Screen("login_form_view")
    data object LoginAuthView : Screen("login_auth_view")

    data object PerfilViewScreen : Screen("perfil_view_screen")

}