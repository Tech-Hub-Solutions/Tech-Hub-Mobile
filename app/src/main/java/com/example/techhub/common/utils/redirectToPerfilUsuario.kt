package com.example.techhub.common.utils

import android.content.Context
import android.os.Bundle
import com.example.techhub.presentation.perfil.PerfilActivity

fun redirectToPerfilUsuario(context: Context, fullName: String, extras: Bundle?) {
    showWelcomeToastWithName(
        context = context,
        fullName = fullName,
    )

    startNewActivity(
        context = context,
        activity = PerfilActivity::class.java,
        bundle = extras
    )
}