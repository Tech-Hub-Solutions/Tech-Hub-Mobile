package com.example.techhub.common.composable
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import com.example.techhub.R
@Composable
fun GitHubTextField(onValueChanged: (String) -> Unit, initialValue: String = "") {
    var filledText by remember { mutableStateOf(initialValue) }
    var isValueValid by remember { mutableStateOf(false) }
    Column {

        androidx.compose.material3.OutlinedTextField(
            value = filledText,
            onValueChange = {
                filledText = it
                onValueChanged(filledText)
            },
            label = { Text("Link do Github") },
            placeholder = { Text("Link do Github") },
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
                    painter = painterResource(id = R.mipmap.github),
                    contentDescription = "Campo Link do Github",
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Go
            ),
        )
    }
}