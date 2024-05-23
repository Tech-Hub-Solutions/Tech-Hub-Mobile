package com.example.techhub.common.utils

import android.content.Context
import android.widget.Toast
import com.example.techhub.R

fun showWelcomeToastWithName(context: Context, fullName: String) {
    val firstName = fullName.split(" ").firstOrNull()
    val welcomeMessage = UiText.StringResource(R.string.toast_text_welcome_with_name)
        .asString(context = context) + "$firstName!"

    val toast: Toast = Toast.makeText(context, welcomeMessage, Toast.LENGTH_SHORT)

    toast.show()
}