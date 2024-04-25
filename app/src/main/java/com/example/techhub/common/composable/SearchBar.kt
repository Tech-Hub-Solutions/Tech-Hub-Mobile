package com.example.techhub.common.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchBar(){
    Scaffold {
        var text = remember { mutableStateOf("") }
        var active = remember {
            mutableStateOf(false) }
        val itens = remember {
            mutableStateListOf("Resende", "Murilo", "Luiz", "Kaio", "Leonardo")
        }


        androidx.compose.material3.SearchBar(
            modifier = Modifier.padding(1.dp).height(50.dp),
            query = text.value,
            onQueryChange = {
                text.value = it
            } ,
            onSearch = {

                active.value = false
            } ,
            active = active.value ,

            onActiveChange = {
                active = mutableStateOf(it)
            },
            placeholder = {
                Text(text = "Pesquisar por nome", fontSize = 15.sp)},

            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Icone de pesquisa")
            },
            trailingIcon = {
                if ( text.value.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable {
                            text.value = ""
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Icone de Limpar",
                    )
                }
            }
        ) {
            itens.forEach{
                Row(modifier = Modifier.padding(all = 14.dp)) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.History,
                        contentDescription = "Icone de Histórico"
                    )
                    Text(text = it)
                }
            }
        }
    }
}