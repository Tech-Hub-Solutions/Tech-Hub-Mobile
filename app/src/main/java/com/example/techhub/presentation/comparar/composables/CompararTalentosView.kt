package com.example.techhub.presentation.comparar.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.techhub.domain.model.flag.FlagData
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompararTalentosView(scaffoldState: BottomSheetScaffoldState, scope: CoroutineScope) {

    val users = remember { SnapshotStateList<UsuarioFavoritoData>() }
//    val selectedUsers = remember { SnapshotStateList<UsuarioFavoritoData>() }
//    var selectedUsers: List<UsuarioFavoritoData> = sampleData
    var selectedUsers: List<AccordionModel> = sampleData

    val accordions = remember { SnapshotStateList<AccordionModel>() }

    val user1 = remember { mutableStateOf(UsuarioFavoritoData()) }
    val user2 = remember { mutableStateOf(UsuarioFavoritoData()) }

    Column(
        modifier = Modifier
            .fillMaxHeight(0.95f)
    ) {
        Spacer(modifier = Modifier.padding(top = 32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardUsuarioComparacao()
            CardUsuarioComparacao()
        }
        Spacer(modifier = Modifier.padding(top = 32.dp))

//        val tecnologias = mutableListOf<FlagData>()
//        val areas = HashSet<String>()
//        val rows = mutableListOf<AccordionModel.Row>()
//
//        // Adiciona todas as flags e áreas dos usuários selecionados
//        selectedUsers.forEach { user ->
//            user.flags?.let { tecnologias.addAll(it) }
//            user.flags?.forEach { it.area?.let { area -> areas.add(area) } }
//        }
//
//        // Cria as linhas para cada área
//        areas.forEach { area ->
//            val flagsGeral = tecnologias.filter { it.area == area }
//            flagsGeral.forEach { flag ->
//                rows.add(
//                    AccordionModel.Row(
//                        tecnologia = flag.nome!!,
//                        user1 = flag in user1.value.flags!!,
//                        user2 = flag in user2.value.flags!!
//                    )
//                )
//            }
//            accordions.add(AccordionModel(area, rows.toList()))
//            rows.clear()
//        }


//        val tecnologias = mutableListOf<FlagData>()
//        val areas = mutableSetOf<String>()
//        val rows = mutableListOf<AccordionModel.Row>()
//
//        // Adiciona todas as flags e áreas dos usuários selecionados
//        selectedUsers.flatMapTo(tecnologias) { it.flags ?: emptyList() }
//        selectedUsers.flatMapTo(areas) { it.flags?.mapNotNull { it.area } ?: emptyList() }
//
//        // Remove duplicatas de flags e áreas
//        tecnologias.distinctBy { it.nome }
//        areas.distinct()
//
//        // Cria as linhas para cada área
//        for (area in areas) {
//            val flagsGeral = tecnologias.filter { it.area == area }
//
//            for (flag in flagsGeral) {
//                rows.add(
//                    AccordionModel.Row(
//                        tecnologia = flag.nome!!,
//                        user1 = flag in user1.value.flags!!,
//                        user2 = flag in user2.value.flags!!
//                    )
//                )
//            }
//
//            accordions.add(AccordionModel(area, rows.toList()))
//            rows.clear() // Limpa as linhas para a próxima iteração da área
//        }


//        tecnologias.addAll(selectedUsers[0].flags!!)
//        tecnologias.addAll(selectedUsers[1].flags!!)
//
//        for (flag in tecnologias) {
//            if (flag !in tecnologias) {
//                tecnologias.add(flag)
//            }
//            if (flag.area !in areas) {
//                areas.add(flag.area!!)
//            }
//        }
//
//        val rows = mutableListOf<AccordionModel.Row>()
//
//        val user1Flags = user1.value.flags
//        val user2Flags = user2.value.flags
//
//        for (area in areas) {
//
//            val flagsGeral = tecnologias.filter { it.area == area }
//
//            for (flag in flagsGeral) {
//                rows.add(
//                    AccordionModel.Row(
//                        tecnologia = flag.nome!!,
//                        user1 = flag in user1Flags!!,
//                        user2 = flag in user2Flags!!
//                    )
//                )
//            }
//
//            accordions.add(AccordionModel(area, rows))
//        }

//        LazyColumn {
//            items(accordions) { accordion ->
//                Accordion(model = accordion)
//            }
//        }

        selectedUsers.forEach {
            Accordion(model = it)
        }

    }
}