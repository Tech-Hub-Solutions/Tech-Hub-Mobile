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
fun CnpjTextField(onValueChanged: (String) -> Unit, context: Context) {
    var filledText by remember { mutableStateOf("") }
    var isCnpjValid by remember { mutableStateOf(true) }

    Column {
        MaskedOutlinedTextField(
            dataType = DataType.CNPJ,
            label = UiText.StringResource(
                R.string.label_cnpj
            ).asString(context = context),
            mask = "##.###.###/####-##",
            isError = !isCnpjValid,
            placeholder = UiText.StringResource(
                R.string.placeholder_cnpj
            ).asString(context = context),
            value = filledText,
            onValueChange = {
                val unmaskedText = it.filter { char -> char.isDigit() }
                if (unmaskedText.length <= 14) {
                    filledText = it
                    onValueChanged(filledText)
                    isCnpjValid = validateCnpj(unmaskedText)
                }
            },
            supportingText = if (!isCnpjValid)
                UiText.StringResource(
                    R.string.toast_error_cnpj
                ).asString(context = context) else "",
            contentDescription = UiText.StringResource(
                R.string.description_image_cnpj
            ).asString(context = context),
        )
    }
}

private fun validateCnpj(cnpj: String): Boolean {
    return cnpj.isEmpty() || cnpj.length == 14
}
