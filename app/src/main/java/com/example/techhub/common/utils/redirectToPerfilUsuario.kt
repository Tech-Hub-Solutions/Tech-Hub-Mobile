package com.example.techhub.common.utils

import android.content.Context
import com.example.techhub.presentation.index.IndexActivity

fun redirectToPerfilUsuario(context: Context, fullName: String) {
    showWelcomeToastWithName(
        context = context,
        fullName = fullName,
    )

    startNewActivity(
        context = context,
        // TODO - Inserir redirecionamento para Activity de Perfil
        // TODO - No parâmetro, passar o pefil e seu token p/ salvar no Data Store
        activity = IndexActivity::class.java,
    )
}