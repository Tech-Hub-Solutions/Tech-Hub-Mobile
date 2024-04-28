package com.example.techhub.presentation.configUsuario.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.composable.EmailTextField
import com.example.techhub.common.composable.FlagDropDownMenu
import com.example.techhub.common.composable.NameTextField
import com.example.techhub.common.composable.PasswordTextField
import com.example.techhub.common.composable.Switch2FA
import com.example.techhub.common.composable.TopBar
import com.example.techhub.common.countryFlagsList
import com.example.techhub.presentation.perfil.PerfilActivity
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConfiguracoesUsuarioView() {

    val isEmpresa = false

    var name by remember { mutableStateOf("") }
    var nacionalidade by remember { mutableStateOf("") }
    val mutableCountryList = countryFlagsList.toList()
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
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp)) {
                Spacer(modifier = Modifier.padding(0.dp))

                NameTextField { name = it }

                FlagDropDownMenu()

                Spacer(modifier = Modifier.padding(0.dp))

                EmailTextField { email = it }

                PasswordTextField { password = it }

                Switch2FA { isUsing2FA = it }

                Spacer(modifier = Modifier.padding(0.dp))
            }

            ElevatedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(PrimaryBlue.value),
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(text = "Salvar", fontSize = 16.sp, fontWeight = FontWeight(500))
            }

            Spacer(modifier = Modifier.padding(12.dp))

            ElevatedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(PrimaryBlue.value),
                ),
                border = BorderStroke(
                    1.dp, Color(PrimaryBlue.value)
                ),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(text = "Cancelar", fontSize = 16.sp, fontWeight = FontWeight(500))
            }

        }
    }
}
