package com.example.techhub.presentation.editarUsuario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.techhub.composable.SetBarColor
import com.example.techhub.presentation.configUsuario.composables.ConfiguracoesUsuarioView
import com.example.techhub.presentation.ui.theme.TechHubTheme

class EditarUsuarioActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechHubTheme {
                SetBarColor(color = Color.White)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EditarUsuarioView()
                }
            }
        }
    }
}
