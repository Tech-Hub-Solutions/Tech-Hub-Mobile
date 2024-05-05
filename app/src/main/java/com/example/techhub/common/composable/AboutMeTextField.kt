package com.example.techhub.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
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
fun AboutMeTextField(onValueChanged: (String) -> Unit, initialValue: String = "") {
    var filledText by remember { mutableStateOf(initialValue) }

    Column {

        androidx.compose.material3.OutlinedTextField(
            value = filledText,
            onValueChange = {
                filledText = it
                onValueChanged(filledText)
            },
            label = { Text("Sobre mim") },
            placeholder = { Text("Sobre mim") },
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
                    contentDescription = "Campo Sobre mim",
                    tint = Color(PrimaryBlue.value)
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Go
            ),
        )
    }
}