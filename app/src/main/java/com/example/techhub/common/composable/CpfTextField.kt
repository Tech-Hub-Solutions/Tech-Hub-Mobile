package com.example.techhub.common.composable

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.techhub.R
import com.example.techhub.common.enums.DataType
import com.example.techhub.common.utils.UiText

@Composable
fun CpfTextField(onValueChanged: (String) -> Unit, context: Context) {
    var filledText by remember { mutableStateOf("") }
    var isCpfValid by remember { mutableStateOf(true) }

    Column {
        MaskedOutlinedTextField(
            dataType = DataType.CPF,
            label = UiText.StringResource(
                R.string.label_cpf
            ).asString(context = context),
            mask = "###.###.###-##",
            isError = !isCpfValid,
            placeholder = UiText.StringResource(
                R.string.placeholder_CPF
            ).asString(context = context),
            value = filledText,
            onValueChange = {
                val unmaskedText = it.filter { char -> char.isDigit() }
                if (unmaskedText.length <= 11) {
                    filledText = it
                    onValueChanged(filledText)
                    isCpfValid = validateCpf(unmaskedText)
                }
            },
            supportingText = if (!isCpfValid)
                UiText.StringResource(
                    R.string.supporting_text_cpf
                ).asString(context) else "",
            contentDescription = UiText.StringResource(
                R.string.description_image_cpf
            ).asString(context = context),
        )
    }
}

private fun validateCpf(cpf: String): Boolean {
    return cpf.isEmpty() || cpf.length == 11
}
