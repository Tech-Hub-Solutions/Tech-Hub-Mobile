package com.example.techhub.presentation.cadastro.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.objects.Constants
import com.example.techhub.common.composable.CardTravaTelaCadastro
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.common.composable.TopBar
import com.example.techhub.common.utils.showToastError
import com.example.techhub.presentation.index.IndexActivity
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import com.example.techhub.presentation.ui.theme.SecondaryBlue

@Composable
fun TravaTelaCadastroView(onUserOptionSelected: (String) -> Unit) {
    var userType: String by remember { mutableStateOf("") }
    val context = LocalContext.current

    fun selectUserType(userSelected: String) {
        userType = userSelected
    }

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = true,
                activity = IndexActivity::class.java,
                context = context,
                title = "Primeiros Passos",
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.fundo_azul),
                contentDescription = "Fundo com tons de azul",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.matchParentSize(),
                alignment = Alignment.TopCenter
            )

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
                        fontWeight = FontWeight.Medium,
                        fontSize = 32.sp,
                    )
                )

                Spacer(modifier = Modifier.padding(16.dp))

                Row {
                    CardTravaTelaCadastro(
                        imagePath = R.mipmap.freelancer_image,
                        contentDescription = "Imagem freelancer",
                        text = "Freelancer",
                        onClick = {
                            selectUserType(userSelected = Constants.FREELANCER)
                        },
                        isSelected = userType == Constants.FREELANCER
                    )

                    Spacer(modifier = Modifier.padding(16.dp))

                    CardTravaTelaCadastro(
                        imagePath = R.mipmap.empresa_image,
                        contentDescription = "Imagem Emppresa",
                        text = "Empresa",
                        onClick = {
                            selectUserType(userSelected = Constants.EMPRESA)
                        },
                        isSelected = userType == Constants.EMPRESA
                    )
                }

                Spacer(modifier = Modifier.padding(120.dp))

                ElevatedButtonTH(
                    onClick = {
                        if (userType.isEmpty() || userType.isBlank()) {
                            val toastErrorMessage = "Selecione uma opção para continuar"
                            showToastError(context = context, message = toastErrorMessage)
                        } else {
                            onUserOptionSelected(userType)
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
}