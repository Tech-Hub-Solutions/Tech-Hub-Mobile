package com.example.techhub.presentation.index.utils

import android.content.Context
import android.widget.Toast
import com.example.techhub.R
import com.example.techhub.common.utils.UiText

fun showWelcomeToast(context: Context, wasToastShowed: Boolean): Boolean? {
    if (!wasToastShowed) {
        val toast: Toast = Toast.makeText(
            context,
            UiText.StringResource(R.string.toast_text_welcome).asString(context = context),
            Toast.LENGTH_SHORT
        )
        toast.show()

        return true
    }
    return null
}