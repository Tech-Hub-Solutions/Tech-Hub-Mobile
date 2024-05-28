package com.example.techhub.common.composable

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import com.example.techhub.R
import com.example.techhub.common.utils.UiText

@Composable
fun LinkedinTextField(
    onValueChanged: (String) -> Unit,
    initialValue: String = "",
    context: Context
) {
    var filledText by remember { mutableStateOf(initialValue) }
    Column {
        OutlinedTextField(
            value = filledText,
            onValueChange = {
                filledText = it
                onValueChanged(filledText)
            },
            label = {
                Text(
                    UiText.StringResource(
                        R.string.text_link_linkedin
                    ).asString(context = context)
                )
            },
            placeholder = {
                Text(
                    UiText.StringResource(
                        R.string.text_link_linkedin
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
                Image(
                    painter = painterResource(id = R.mipmap.linkedin),
                    contentDescription = UiText.StringResource(
                        R.string.description_image_link_linkedin
                    ).asString(context = context),
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Done
            ),
        )
    }
}