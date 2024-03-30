package com.example.techhub.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.techhub.ui.theme.PrimaryBlue

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(onValueChanged: (String) -> Unit) {
    var filledText by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column {
        LaunchedEffect(filledText) {
            isPasswordValid = validatePassword(filledText)
        }

        OutlinedTextField(
            value = filledText,
            onValueChange = {
                filledText = it
                isPasswordValid = validatePassword(filledText)
                onValueChanged(filledText)
            },
            label = { Text("Senha") },
            placeholder = { Text("Digite sua senha") },
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
                    imageVector = Icons.Filled.Key,
                    contentDescription = "campo para a senha",
                    tint = Color(PrimaryBlue.value)
                )
            },
            trailingIcon = {
                if (filledText.isNotEmpty()) {
                    IconButton(onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }, enabled = true) {
                        if (isPasswordVisible) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "Mostrar senha",
                                tint = Color.Black
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "Esconder senha",
                                tint = Color.Black
                            )
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            isError = isPasswordValid,
            supportingText = {
                if (isPasswordValid && filledText.isNotBlank()) Text("Senha inválida")
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Go,
            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )
    }
}

fun validatePassword(password: String): Boolean {
    val specialCharRegex = Regex("[!@#\$%^&*(),.?\":{}|<>]")
    val uppercaseRegex = Regex("[A-Z]")
    val lowercaseRegex = Regex("[a-z]")
    val digitRegex = Regex("[0-9]")

    return !(password.isBlank() || password.length in 6..20 &&
            specialCharRegex.containsMatchIn(password) &&
            uppercaseRegex.containsMatchIn(password) &&
            lowercaseRegex.containsMatchIn(password) &&
            digitRegex.containsMatchIn(password))
}