package com.example.techhub.composable

import android.content.Context
import android.content.Intent
import android.os.Bundle

fun StartNewActivity(context: Context, activity: Class<*>, bundle: Bundle? = null) {
    val intent = Intent(context, activity)

    if (bundle != null) {
        intent.putExtras(bundle)
    }

    context.startActivity(intent)
}