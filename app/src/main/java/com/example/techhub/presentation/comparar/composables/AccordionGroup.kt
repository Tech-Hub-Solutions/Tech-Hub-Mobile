package com.example.techhub.presentation.comparar.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun AccordionGroup(group: List<AccordionModel>) {
    Column {
        group.forEach {
            Accordion(model = it)
        }
    }
}