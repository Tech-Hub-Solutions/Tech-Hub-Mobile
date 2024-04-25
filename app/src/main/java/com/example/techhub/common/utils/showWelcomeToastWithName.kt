package com.example.techhub.common.utils

import android.content.Context
import android.widget.Toast

fun showWelcomeToastWithName(context: Context, fullName: String) {
    val firstName = fullName.split(" ").firstOrNull()
    val welcomeMessage = "Bem-vindo, $firstName!"

    val toast: Toast = Toast.makeText(context, welcomeMessage, Toast.LENGTH_SHORT)

    toast.show()
}