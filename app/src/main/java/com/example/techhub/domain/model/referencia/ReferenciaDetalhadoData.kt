package com.example.techhub.domain.model.referencia

data class ReferenciaDetalhadoData(
    val idReferencia: Int? = null,
    val nomeAvaliador: String? = null,
    val nomeAvaliado: String? = null,
    val isFavoritado: Boolean? = null,
    val isRecomendado: Boolean? = null
)
