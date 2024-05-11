package com.example.techhub.domain.model.github

import com.google.gson.annotations.SerializedName

data class Repositorio (
    val name: String? = null,
    val description: String? = null,
    val language: String? = null,
    var languages: List<String> = emptyList(),
    @field:SerializedName("html_url") val url: String? = null,
)