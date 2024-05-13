package com.example.techhub.presentation.perfil.composables.avaliacao

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.utils.UiText

@Composable
fun AvaliacaoCountRow(
    titleNumber: String,
    qtdEstrela: Int,
    totalProgress: Float
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = "$titleNumber " + if (titleNumber == "1")
                UiText.StringResource(
                    R.string.text_star
                ).asString(context = context)
            else
                UiText.StringResource(
                    R.string.text_stars
                ).asString(context = context),
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier
                .weight(1f)
        )

        AvaliacaoProgressIndicator(
            totalProgress = totalProgress,
            modifier = Modifier.weight(3f)
        )

        Text(
            text = "($qtdEstrela)",
            color = Color.Black,
            modifier = Modifier
                .weight(0.6f),
            textAlign = TextAlign.End
        )
    }
}