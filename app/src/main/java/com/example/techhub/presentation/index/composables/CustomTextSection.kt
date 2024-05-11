package com.example.techhub.presentation.index.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun CustomTextSection() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(vertical = 40.dp, horizontal = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 32.sp
                    )
                ) {
                    // TODO - NECESSIDADE DE VER COMO FICARÁ COM O i18n
                    append("O ponto de encontro dos ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color(PrimaryBlue.value),
                        fontWeight = FontWeight.Medium,
                        fontSize = 32.sp
                    )
                ) {
                    append("talentos")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 32.sp
                    )
                ) {
                    append("!")
                }
            },
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = UiText.StringResource(
                R.string.subtitle_index
            ).asString(context = context),
            color = Color(GrayText.value),
            fontWeight = FontWeight.Thin,
            fontSize = 17.sp
        )
    }
}