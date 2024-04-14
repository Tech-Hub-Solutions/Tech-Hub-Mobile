package com.example.techhub.presentation.comparar.composables

import android.inputmethodservice.Keyboard.Row

data class AccordionModel (
    val tecnologia: String,
    val rows: List<Row>
) {
    data class Row(
        val title: String,
        val description: String
    )
}