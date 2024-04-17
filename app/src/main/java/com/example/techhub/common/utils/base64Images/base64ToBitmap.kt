package com.example.techhub.common.utils.base64Images

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

fun String.base64ToBitmap(): Bitmap {
    Base64.decode(this, Base64.DEFAULT).apply {
        return BitmapFactory.decodeByteArray(this, 0, size)
    }
}