package com.example.techhub.common.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

fun copyToClipBoard(context: Context, copyText: String, toastText: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label", copyText)
    clipboard.setPrimaryClip(clip)

    val toast = Toast.makeText(context, toastText, Toast.LENGTH_SHORT)
    toast.show()
}