package com.example.techhub.common.utils.base64Images

import java.net.URLDecoder

fun base64ToSafeSecretUrlDecoded(secretQrCodeUrl: String): String {
    val safeSecretQrCodeUrl = secretQrCodeUrl?.replace("+", "%2B")
    val decodedUrl = safeSecretQrCodeUrl?.let { URLDecoder.decode(it, "UTF-8") }

    return decodedUrl!!
}