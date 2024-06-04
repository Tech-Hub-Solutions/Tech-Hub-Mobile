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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.techhub.common.composable.UserCard
import com.example.techhub.domain.model.accordion.AccordionModel
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import com.example.techhub.presentation.comparar.composables.accordion.Accordion

@Composable
fun CompararTalentosView(
    selectedUsers: SnapshotStateList<UsuarioFavoritoData>,
) {
    val tecnologias = remember {
        mutableStateMapOf(Pair("", mutableListOf("")))
    }

    selectedUsers.forEach { it ->
        if (it.flags == null) return@forEach;

        val flags = it.flags;

        flags.forEach {
            val area = it.area;
            val nome = it.nome;

            if (tecnologias.containsKey(area)) {
                val list = tecnologias[area];
                if (tecnologias[area]?.contains(nome!!) == false) list?.add(nome!!)
            } else {
                tecnologias[area!!] = mutableListOf(nome!!);
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight(0.95f)
    ) {
        Spacer(modifier = Modifier.padding(top = 32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UserCard(
                userProfile = selectedUsers.get(0),
                selectedUsers = selectedUsers,
                isAbleToFavorite = false,
                modifier = Modifier.weight(0.5f),
                isAbleToCompare = null
            )
            UserCard(
                userProfile = selectedUsers.get(1),
                selectedUsers = selectedUsers,
                isAbleToFavorite = false,
                modifier = Modifier.weight(0.5f),
                isAbleToCompare = null
            )
        }

        Spacer(modifier = Modifier.padding(top = 32.dp))

        val tecnologiasDoUsuario1 = selectedUsers[0].flags?.map { flag -> flag.nome }
        val tecnologiasDoUsuario2 = selectedUsers[1].flags?.map { flag -> flag.nome }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            items(items = tecnologias.toList()) {

                val area = it.first;
                val tecnologias = it.second;

                if (area.equals("")) return@items

                Accordion(AccordionModel(
                    area = area,
                    rows = tecnologias.map { tecnologia ->
                        AccordionModel.Row(
                            tecnologia,
                            tecnologiasDoUsuario1?.contains(tecnologia) ?: false,
                            tecnologiasDoUsuario2?.contains(tecnologia) ?: false
                        )
                    }
                ))
            }
        }
    }
}