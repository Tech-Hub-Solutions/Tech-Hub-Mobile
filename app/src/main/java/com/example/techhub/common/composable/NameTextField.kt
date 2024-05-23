package com.example.techhub.common.composable

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun NameTextField(
    onValueChanged: (String) -> Unit,
    initialValue: String = "",
    context: Context,
    modifier: Modifier? = null
) {
    var filledText by remember { mutableStateOf(initialValue) }
    var isNameValid by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = filledText,
            onValueChange = {
                filledText = it
                isNameValid = filledText.isBlank()
                onValueChanged(filledText)
            },
            label = {
                Text(
                    UiText.StringResource(
                        R.string.subtitle_name
                    ).asString(context = context)
                )
            },
            placeholder = {
                Text(
                    UiText.StringResource(
                        R.string.placeholder_text_name
                    ).asString(context = context)
                )
            },
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
                    imageVector = Icons.Filled.Person,
                    contentDescription = UiText.StringResource(
                        R.string.description_image_nome
                    ).asString(context = context),
                    tint = Color(PrimaryBlue.value)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .let {
                    if (modifier != null) it.then(modifier) else it
                },
            singleLine = true,
            isError = isNameValid,
            supportingText = {
                if (filledText.isBlank() && isNameValid) Text(
                    UiText.StringResource(
                        R.string.supporting_text_nome
                    ).asString(context = context)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Go
            ),
        )
    }
}