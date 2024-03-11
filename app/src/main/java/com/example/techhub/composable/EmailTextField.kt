package com.example.techhub.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.core.util.PatternsCompat
import com.example.techhub.ui.theme.PrimaryBlue

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailTextField() {
    var filledText by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }

    Column {
        LaunchedEffect(filledText) {
            isEmailValid = validateEmail(filledText)
        }

        OutlinedTextField(
            value = filledText,
            onValueChange = {
                filledText = it
                isEmailValid = validateEmail(filledText)
            },
            label = { Text("E-mail") },
            placeholder = { Text("Digite seu e-mail") },
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
                    imageVector = Icons.Filled.Mail,
                    contentDescription = "campo para o e-mail",
                    tint = Color(PrimaryBlue.value)
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            isError = isEmailValid,
            supportingText = {
                if (isEmailValid && filledText.isNotBlank()) Text("E-mail inválido")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Go
            ),
        )
    }
}

private fun validateEmail(email: String): Boolean {
    return !(email.isBlank() || (PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()))
}