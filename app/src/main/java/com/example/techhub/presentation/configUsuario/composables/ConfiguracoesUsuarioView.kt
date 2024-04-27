package com.example.techhub.presentation.configUsuario.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.techhub.common.Constants
import com.example.techhub.common.Screen
import com.example.techhub.common.composable.BottomBar
import com.example.techhub.common.composable.CnpjTextField
import com.example.techhub.common.composable.CpfTextField
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.common.composable.EmailTextField
import com.example.techhub.common.composable.NameTextField
import com.example.techhub.common.composable.PasswordTextField
import com.example.techhub.common.composable.Switch2FA
import com.example.techhub.common.composable.TopBar
import com.example.techhub.presentation.perfil.PerfilActivity
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConfiguracoesUsuarioView() {

    val isEmpresa = false

    var name by remember { mutableStateOf("") }
    var nacionalidade by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isUsing2FA by remember { mutableStateOf(false) }
    val toastErrorMessage = "Ops! Algo deu errado.\n Tente novamente."

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = true,
                activity = PerfilActivity::class.java,
                context = LocalContext.current,
                title = "Editar Configurações",
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 24.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(12.dp))

            NameTextField { name = it }

            Spacer(modifier = Modifier.padding(12.dp))

            if (isEmpresa) {

            } else {

            }

            Spacer(modifier = Modifier.padding(12.dp))

            EmailTextField { email = it }

            Spacer(modifier = Modifier.padding(12.dp))

            PasswordTextField { password = it }

            Switch2FA { isUsing2FA = it }

            Spacer(modifier = Modifier.padding(12.dp))

            ElevatedButtonTH(
                onClick = {

                },
                text = "Salvar",
                backgroundColor = Color(PrimaryBlue.value),
                textColor = Color.White,
                width = (350),
                height = (60),
            )

            ElevatedButtonTH(
                onClick = {

                },
                text = "Cancelar",
                backgroundColor = Color.White,
                textColor = Color(PrimaryBlue.value),
                width = (350),
                height = (60)
            )
        }
    }
}
