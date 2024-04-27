package com.example.techhub.common.utils

import android.content.Context
import com.example.techhub.presentation.perfil.PerfilActivity

fun redirectToPerfilUsuario(context: Context, fullName: String) {
    showWelcomeToastWithName(
        context = context,
        fullName = fullName,
    )

    startNewActivity(
        context = context,
        // TODO - No parâmetro, passar o pefil e seu token p/ salvar no Data Store
        activity = PerfilActivity::class.java,
    )
}