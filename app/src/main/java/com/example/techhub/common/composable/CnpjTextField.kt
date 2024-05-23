package com.example.techhub.common.composable

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.techhub.R
import com.example.techhub.common.utils.UiText

@Composable
fun CnpjTextField(onValueChanged: (String) -> Unit, context: Context) {
    var filledText by remember { mutableStateOf("") }
    var isCnpjValid by remember { mutableStateOf(false) }

    Column {
        MaskedOutlinedTextField(
            label = UiText.StringResource(
                R.string.label_cnpj
            ).asString(context = context),
            mask = "##.###.###/####-##",
            isError = isCnpjValid,
            placeholder = UiText.StringResource(
                R.string.placeholder_cnpj
            ).asString(context = context),
            value = filledText,
            onValueChange = {
                val unmaskedText = it.filter { char -> char.isDigit() }
                if (unmaskedText.length <= 14) {
                    filledText = it
                    isCnpjValid = !isValid(unmaskedText)
                    onValueChanged(filledText)
                }
            },
            supportingText = if (isCnpjValid)
                UiText.StringResource(
                    R.string.toast_error_cnpj
                ).asString(context = context) else "",
            contentDescription = UiText.StringResource(
                R.string.description_image_cnpj
            ).asString(context = context),
        )
    }
}

fun isValid(cnpj: String): Boolean {
    return validateCNPJLength(cnpj) && validateCNPJRepeatedNumbers(cnpj)
            && validateCNPJVerificationDigit(true, cnpj)
            && validateCNPJVerificationDigit(false, cnpj)
}

private fun validateCNPJLength(cnpj: String) = cnpj.length == 14

private fun validateCNPJRepeatedNumbers(cnpj: String): Boolean {
    return (0..9)
        .map { it.toString().repeat(14) }
        .map { cnpj == it }
        .all { !it }
}

private fun validateCNPJVerificationDigit(firstDigit: Boolean, cnpj: String): Boolean {
    val startPos = when (firstDigit) {
        true -> 11
        else -> 12
    }
    val weightOffset = when (firstDigit) {
        true -> 0
        false -> 1
    }
    val sum = (startPos downTo 0).fold(0) { acc, pos ->
        val weight = 2 + ((11 + weightOffset - pos) % 8)
        val num = cnpj[pos].toString().toInt()
        val sum = acc + (num * weight)
        sum
    }
    val result = sum % 11
    val expectedDigit = when (result) {
        0, 1 -> 0
        else -> 11 - result
    }

    val actualDigit = cnpj[startPos + 1].toString().toInt()

    return expectedDigit == actualDigit
}