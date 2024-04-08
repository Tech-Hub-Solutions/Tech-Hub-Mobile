package com.example.techhub.common.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle

fun startNewActivity(context: Context, activity: Class<*>, bundle: Bundle? = null) {
    Intent(context, activity).apply {
        bundle?.let { putExtras(it) }
        context.startActivity(this)
    }
}