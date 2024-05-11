package com.example.techhub.presentation.comparar.composables.accordion

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.techhub.domain.model.accordion.AccordionModel

@Composable
fun AccordionGroup(group: List<AccordionModel>) {
    Column {
        group.forEach {
            Accordion(model = it)
        }
    }
}