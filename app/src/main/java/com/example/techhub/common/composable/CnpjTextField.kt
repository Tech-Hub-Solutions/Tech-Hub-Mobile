package com.example.techhub.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun CnpjTextField(onValueChanged: (String) -> Unit) {
    var filledText by remember { mutableStateOf("") }
    var isCnpjValid by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = filledText,
            onValueChange = {
                filledText = it
                isCnpjValid = isCnpjValidTemp(filledText)
                onValueChanged(filledText)
            },
            label = { Text("CNPJ") },
            placeholder = { Text("Digite seu CNPJ") },
            textStyle = LocalTextStyle.current.copy(
                color = Color.Black,
                fontSize = 16.sp
            ),
            colors = TextFieldDefaults.colors(
                cursorColor = Color(PrimaryBlue.value),
                errorCursorColor = Color(PrimaryBlue.value),
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                errorSupportingTextColor = Color.Red.copy(alpha = 0.6f),
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.AssignmentInd,
                    contentDescription = "campo para o CNPJ",
                    tint = Color(PrimaryBlue.value)
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            isError = isCnpjValid,
            supportingText = {
                if (isCnpjValid) Text("CNPJ inválido")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Go
            ),
        )
    }
}


fun isCnpjValidTemp(cnpj: String): Boolean {
    return cnpj.length != 14
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

fun isCNPJ(document: String): Boolean {
    if (document.isEmpty() || document.length != 14) return false

    val numbers = document.filter { it.isDigit() }.map {
        it.toString().toInt()
    }.toMutableList()

    //repeticao
    if (numbers.all { it == numbers[0] }) return false

    //digito 1
    val dv1 = 11 - (arrayOf(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2).mapIndexed { index, i ->
        i * numbers[index]
    }).sum().rem(11)
    numbers.add(dv1)

    //digito 2
    val dv2 = 11 - (arrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2).mapIndexed { index, i ->
        i * numbers[index]
    }).sum().rem(11)

    return numbers[12] == dv1 && numbers[13] == dv2
}