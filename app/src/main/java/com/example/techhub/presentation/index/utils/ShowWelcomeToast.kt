package com.example.techhub.presentation.index.utils

import android.content.Context
import android.widget.Toast

fun showWelcomeToast(context: Context, wasToastShowed: Boolean): Boolean? {
    if (!wasToastShowed) {
        val toast: Toast = Toast.makeText(context, "Seja bem-vindo(a)!", Toast.LENGTH_SHORT)
        toast.show()

        return true
    }
    return null
}