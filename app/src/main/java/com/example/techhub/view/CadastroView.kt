package com.example.techhub.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    Scaffold(
        topBar = {
            TopBar(navController = navController, title = "Cadastro", route = Screen.CadastroScreen.route)
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

            Spacer(modifier = Modifier.padding(40.dp))
            
            ImageSection()

            Spacer(modifier = Modifier.padding(194.dp))
            ButtonSection()
        }
    }


}


@Composable
fun ImageSection(){
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
            textColor = Color(PrimaryBlue.value)
        )

        Spacer(modifier = Modifier.padding(16.dp))

        EditableForm(
            height = 148.0,
            width = 148.0,
            backcroundColor = Color.White,
            imagePath = R.mipmap.empresa_image,
            contentDescription = "Imagem freelancer",
            text = "Freelancer",
            textColor = Color(PrimaryBlue.value)
        )
    }
}

@Composable
fun ButtonSection(){

    ElevatedButtonTH(
        onClick = { /*TODO*/ },
        text = "Avançar",
        backgroundColor = Color(PrimaryBlue.value),
        width = 311,
        height = 48
    )
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