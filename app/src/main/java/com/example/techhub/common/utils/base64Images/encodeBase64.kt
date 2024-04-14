package com.example.techhub.common.utils.base64Images

import java.net.URLEncoder

fun encodeBase64(base64Image: String): String {
    return URLEncoder.encode(base64Image, "UTF-8")
}