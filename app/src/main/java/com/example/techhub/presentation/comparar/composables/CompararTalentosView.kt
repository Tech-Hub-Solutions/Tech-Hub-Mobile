package com.example.techhub.presentation.comparar.composables

import android.util.Log
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.techhub.domain.model.flag.FlagData
import com.example.techhub.domain.model.usuario.UsuarioFavoritoData
import kotlinx.coroutines.CoroutineScope

@Composable
fun CompararTalentosView(
    scope: CoroutineScope,
    selectedUsers: SnapshotStateList<UsuarioFavoritoData>
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

            // area = backend
            if (tecnologias.containsKey(area)) {
                val list = tecnologias[area];
                list?.add(nome!!);
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardUsuarioComparacao()
            CardUsuarioComparacao()
        }
        Spacer(modifier = Modifier.padding(top = 32.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            items(items = tecnologias.toList()) {

                val area = it.first;
                val tecnologias = it.second;

                if (area.equals("")) return@items

                val tecnologiasDoUsuario1 = selectedUsers[0].flags?.map { flag -> flag.nome }
                val tecnologiasDoUsuario2 = selectedUsers[1].flags?.map { flag -> flag.nome }

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