package com.example.techhub.presentation.index.composables

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.presentation.cadastro.CadastroActivity
import com.example.techhub.presentation.login.LoginActivity
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import com.example.techhub.presentation.ui.theme.dimensions

@Composable
fun ButtonsSection(context: Context) {
    Column {
        ElevatedButtonTH(
            onClick = { startNewActivity(context = context, LoginActivity::class.java) },
            text = "Entrar",
            backgroundColor = Color(PrimaryBlue.value),
            width = (MaterialTheme.dimensions.buttonWidth),
            height = (MaterialTheme.dimensions.buttonHeight),
        )

        Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimensions.small1))

        ElevatedButtonTH(
            onClick = {
                startNewActivity(
                    context = context,
                    CadastroActivity::class.java
                )
            },
            text = "Cadastrar",
            backgroundColor = Color(Color.White.value),
            textColor = Color(PrimaryBlue.value),
            width = (MaterialTheme.dimensions.buttonWidth),
            height = (MaterialTheme.dimensions.buttonHeight),
        )
    }
}