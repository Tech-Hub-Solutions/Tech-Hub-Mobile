package com.example.techhub.domain.model.usuario

data class Pageable(
    val sort: Sort,
    val offset: Long,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val unpaged: Boolean
)
