package com.example.techhub.common.utils

import androidx.compose.ui.graphics.Color
import com.example.techhub.presentation.ui.theme.AnaliseDados
import com.example.techhub.presentation.ui.theme.BackEnd
import com.example.techhub.presentation.ui.theme.BancoDeDados
import com.example.techhub.presentation.ui.theme.DevOps
import com.example.techhub.presentation.ui.theme.FrontEnd
import com.example.techhub.presentation.ui.theme.IA
import com.example.techhub.presentation.ui.theme.Mobile
import com.example.techhub.presentation.ui.theme.Seguranca
import com.example.techhub.presentation.ui.theme.SoftSkills
import com.example.techhub.presentation.ui.theme.Testes

fun verificarCorFlag(flag: String): Color {
    return when (flag.lowercase()) {
        "front-end" -> FrontEnd
        "back-end" -> BackEnd
        "mobile" -> Mobile
        "banco de dados" -> BancoDeDados
        "testes" -> Testes
        "análise de dados" -> AnaliseDados
        "devops" -> DevOps
        "inteligência artificial" -> IA
        "segurança" -> Seguranca
        else -> SoftSkills
    }
}