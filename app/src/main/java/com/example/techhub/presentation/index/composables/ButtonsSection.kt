package com.example.techhub.presentation.index.composables

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.presentation.cadastro.CadastroActivity
import com.example.techhub.presentation.login.LoginActivity
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun ButtonsSection(context: Context) {
    Column {
        ElevatedButtonTH(
            onClick = { startNewActivity(context = context, LoginActivity::class.java) },
            text = "Entrar",
            backgroundColor = Color(PrimaryBlue.value),
            width = (350),
            height = (60),
        )

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
            width = (350),
            height = (60),
        )
    }
}