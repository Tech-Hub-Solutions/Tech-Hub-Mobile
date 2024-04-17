package com.example.techhub.common.utils

import android.content.Context
import android.widget.Toast

fun showToastError(context: Context, message: String) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}