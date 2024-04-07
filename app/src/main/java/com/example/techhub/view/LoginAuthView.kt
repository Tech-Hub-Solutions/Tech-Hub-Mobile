package com.example.techhub.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.techhub.R
import com.example.techhub.composable.CenteredImageSection
import com.example.techhub.composable.ElevatedButtonTH
import com.example.techhub.composable.TopBar
import com.example.techhub.ui.theme.GrayButtonText
import com.example.techhub.ui.theme.PrimaryBlue
import com.example.techhub.utils.Screen

@Composable
fun LoginAuth(navController: NavController) {
    var filledText by remember { mutableStateOf("") }
    val context: Context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = true,
                title = "Login",
                activity = LoginActivity::class.java,
                context = context
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

            CenteredImageSection(
                imagePath = R.mipmap.login_auth_lock_image,
                contentDescription = "@string/description_image_login_auth",
                width = 252,
                height = 300,
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Insira o código de autorização",
                    color = Color(PrimaryBlue.value),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    lineHeight = 38.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.padding(16.dp))

            OutlinedTextField(
                value = filledText,
                onValueChange = {
                    filledText = it
                },
                label = { Text("Código de verificação") },
                placeholder = { Text("Digite o código") },
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                ),
                colors = TextFieldDefaults.colors(
                    cursorColor = Color(PrimaryBlue.value),
                    errorCursorColor = Color(PrimaryBlue.value),
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    errorSupportingTextColor = Color.Red.copy(alpha = 0.6f),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Go,
                ),
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { navController.navigate(Screen.LoginScreen.route) },
                    modifier = Modifier
                        .width(130.dp)
                        .height(52.dp)
                ) {
                    Text(
                        text = "Cancelar",
                        color = Color(GrayButtonText.value),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(300)
                    )
                }

                ElevatedButtonTH(
                    onClick = { /*TODO*/ },
                    text = "Continuar",
                    backgroundColor = Color(PrimaryBlue.value),
                    textColor = Color.White,
                    width = 130,
                    height = 52,
                )
            }
        }
    }
}

