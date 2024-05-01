package com.example.techhub.domain.model.flag

import java.io.Serializable


data class FlagData(
    val id: Int? = null,
    val nome: String? = null,
    val area: String? = null,
    val categoria: String? = null,
) : Serializable
