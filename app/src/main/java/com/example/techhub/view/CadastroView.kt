package com.example.techhub.view

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.techhub.EditableForm
import com.example.techhub.R
import com.example.techhub.composable.ElevatedButtonTH
import com.example.techhub.composable.TopBar
import com.example.techhub.ui.theme.PrimaryBlue
import com.example.techhub.ui.theme.SecondaryBlue
import com.example.techhub.utils.Screen

@Composable
fun CadastroView(navController: NavController) {
    val userType = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopBar(navController = navController, title = "Primeiros Passos", route = Screen.IndexScreen.route)
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.padding(20.dp))

            Text(
                text = "Como deseja começar?",
                style = TextStyle(
                    color = Color(SecondaryBlue.value),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Row(){
                EditableForm(
                    height = 148.0,
                    width = 148.0,
                    backcroundColor = Color.White,
                    imagePath = R.mipmap.freelancer_image,
                    contentDescription = "Imagem freelancer",
                    text = "Freelancer",
                    textColor = Color(PrimaryBlue.value),
                    onClick = {userType.value = 1}
                )

                Spacer(modifier = Modifier.padding(16.dp))

                EditableForm(
                    height = 148.0,
                    width = 148.0,
                    backcroundColor = Color.White,
                    imagePath = R.mipmap.empresa_image,
                    contentDescription = "Imagem Emppresa",
                    text = "Empresa",
                    textColor = Color(PrimaryBlue.value),
                    onClick = {userType.value = 2}
                )
            }

            Spacer(modifier = Modifier.padding(120.dp))

            ElevatedButtonTH(
                onClick = {
                    if (userType.value == 1){
                        navController.navigate(Screen.CadastroFreelancerScreen.route)
                    } else if (userType.value == 2){
                        navController.navigate(Screen.CadastroEmpresaScreen.route)
                    }
                      },
                text = "Avançar",
                backgroundColor = Color(PrimaryBlue.value),
                width = (350),
                height = (60)
            )

        }
    }


}


@Composable
fun ImageSection(navController: NavController, userType: Int){

}

@Composable
fun ButtonSection(){

}

@Composable
fun BackgroundImage(){
    Image(painter = painterResource(
        id = R.mipmap.fundo_azul),
        contentDescription = "Background azul",
        modifier = Modifier.fillMaxSize(),
        alignment = Alignment.TopStart
    )
}