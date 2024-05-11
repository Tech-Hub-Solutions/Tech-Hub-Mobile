package com.example.techhub.domain.model.accordion

data class AccordionModel (
    val area: String,
    val rows: List<Row>
) {
    data class Row(
        val tecnologia: String,
        val user1: Boolean,
        val user2: Boolean
    )
}