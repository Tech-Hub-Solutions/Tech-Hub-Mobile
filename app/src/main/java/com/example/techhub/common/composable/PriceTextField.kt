package com.example.techhub.common.composable

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
fun PriceTextField(
    onValueChanged: (Double?) -> Unit,
    initialValue: Double? = 0.0,
    context: Context
) {
    var filledText by remember { mutableStateOf(initialValue) }
    var isValueValid by remember { mutableStateOf(false) }
    Column {

        OutlinedTextField(
            value = if (filledText == 0.0 || filledText == null) "" else "R$ ${
                String.format("%.2f", filledText)
            }",
            onValueChange = {
                val cleanValue = it.removePrefix("R$ ")

                var doubleValue = try {
                    cleanValue.toDouble()
                } catch (e: Exception) {
                    filledText
                }

                if (doubleValue != null && doubleValue < 0) {
                    doubleValue = 0.0
                }

                filledText = String.format("%.2f", doubleValue).toDouble()
                onValueChanged(filledText)
            },
            label = {
                Text(
                    UiText.StringResource(
                        R.string.subtitle_price
                    ).asString(context = context)
                )
            },
            placeholder = {
                Text(
                    UiText.StringResource(
                        R.string.subtitle_price
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
                    imageVector = Icons.Filled.CurrencyExchange,
                    contentDescription = UiText.StringResource(
                        R.string.description_image_service_price
                    ).asString(context = context),
                    tint = Color(PrimaryBlue.value)
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            isError = isValueValid,
            supportingText = {
                if (isValueValid) {
                    Text(
                        UiText.StringResource(
                            R.string.supporting_text_service_price
                        ).asString(context = context)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Go
            ),
        )
    }
}