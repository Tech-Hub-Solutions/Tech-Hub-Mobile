package com.example.techhub.presentation.perfil.composables.tag

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.techhub.common.utils.verificarCorFlag
import com.example.techhub.domain.model.flag.FlagData
import com.example.techhub.presentation.perfil.composables.SectionTitle

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSection(title: String, flags: List<FlagData> = emptyList()) {
    Column {
        SectionTitle(title = title, isCentered = false)

        FlowRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement =  Arrangement.spacedBy(8.dp),
        ) {
            flags.forEach { flag ->
                Tag(title = flag.nome ?: "", backgroundColor = verificarCorFlag(flag.area?: ""))
            }
        }
    }
}