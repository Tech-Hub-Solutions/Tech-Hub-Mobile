package com.example.techhub.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DensitySmall
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.techhub.composable.BottomBar
import com.example.techhub.composable.OrderDropDownMenu
import com.example.techhub.composable.TopBar
import com.example.techhub.composable.UserCard
import com.example.techhub.service.RetrofitService
import com.example.techhub.service.flag.dto.FlagData
import com.example.techhub.service.usuario.dto.PageableDto
import com.example.techhub.service.usuario.dto.UsuarioFavoritoData
import com.example.techhub.ui.theme.GrayButtonText
import com.example.techhub.utils.Screen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



@Composable
fun FavoritosView(navController: NavController){
    val usersList = remember { SnapshotStateList<UsuarioFavoritoData>() }
    val expanded by remember { mutableStateOf(false) }
    val ordem = remember{ mutableStateOf("avaliacao,desc") }
    val erroApi = remember { mutableStateOf("") }
    val loading = remember{ mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopBar(navController = navController, title = "Favoritos", route = Screen.IndexScreen.route)
        },
        bottomBar = { BottomBar(isEmpresa = true) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top) {
                Text(text = "${usersList.size} profissionais encontrados")

                Spacer(modifier = Modifier.padding(horizontal = 10.dp))

                OrderDropDownMenu(ordem)
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            LaunchedEffect(Unit) {
                getFavoriteUsers(usersList, erroApi, ordem)
            }

            if (usersList.isNotEmpty()) {
                Text(text = "tamanho da lista ${usersList.size}")
                LazyColumn (modifier = Modifier
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .height(562.dp)){
                    val subLists = usersList.chunked(2)

                    itemsIndexed(subLists){ index, subLista ->
                        Row (modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            for (item in subLista) {
                                UserCard(item, usersList)
                            }
                        }
                    }
                }
            }

        }
    }
}


fun getFavoriteUsers(
    usersList:SnapshotStateList<UsuarioFavoritoData>,
    erroApi: MutableState<String>,
    ordem:MutableState<String>,
    ){
    val token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXRhaW5ub3ZhdGVAaG90bWFpbC5jb20iLCJpYXQiOjE3MTI3MDQxODMsImV4cCI6MTcxNjMwNDE4M30.Q8sYDnw5IBugPEMQWqiPqMKfTpdqk9pJ9o31P4pierXOzqBiPsH_IKbKN2HK2okURUIPWVVzqPlZ2xIxN3l6XQ"
    val page = PageableDto(
        0,
        10,
        ""
    )

    val call = RetrofitService.getFavoriteUsers().getFavoriteUsers(token,page.page,
        page.size, page.sort,ordem.value)

    call.enqueue(object : Callback<SnapshotStateList<UsuarioFavoritoData>>{
        override fun onResponse(
            call: Call<SnapshotStateList<UsuarioFavoritoData>>,
            response: Response<SnapshotStateList<UsuarioFavoritoData>>
        ) {
            val responseBody = response.body()
            if (response.isSuccessful) {
                if (responseBody != null) {
                    usersList.clear()
                    usersList.addAll(responseBody)
                }
                erroApi.value = ""
            } else {
                erroApi.value = response.errorBody()!!.string()
            }
        }

        override fun onFailure(call: Call<SnapshotStateList<UsuarioFavoritoData>>, t: Throwable) {
            erroApi.value = t.message!!
        }
    })
}

