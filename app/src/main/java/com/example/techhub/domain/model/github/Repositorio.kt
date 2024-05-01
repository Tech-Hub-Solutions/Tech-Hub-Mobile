package com.example.techhub.domain.model.github

data class Repositorio (
    val name: String? = null,
    val description: String? = null,
    val language: String? = null,
    var languages: List<String> = emptyList(),
    val url: String? = null,
)