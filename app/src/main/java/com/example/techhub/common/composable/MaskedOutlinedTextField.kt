package com.example.techhub.common.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun MaskedOutlinedTextField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (newValue: String) -> Unit,
    mask: String? = null,
    visualTransformation: VisualTransformation? = null,
    supportingText: String? = null,
    contentDescription: String? = null,
    isError: Boolean = false
) {
    val transformation = if (mask != null && visualTransformation == null) {
        val offsetMapping = createOffsetMapping(mask)
        VisualTransformation { text ->
            val transformedText = applyMask(mask, text.text)
            TransformedText(
                text = AnnotatedString(transformedText),
                offsetMapping = offsetMapping
            )
        }
    } else {
        VisualTransformation.None
    }

    OutlinedTextField(
        value = value,
        singleLine = true,
        onValueChange = {
            onValueChange(it)
        },
        enabled = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        label = {
            Row(verticalAlignment = Alignment.Top) {
                Text(text = label)
            }
        },
        shape = RoundedCornerShape(7.dp),
        placeholder = {
            Text(text = placeholder)
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
        supportingText = { Text(text = supportingText!!) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.AssignmentInd,
                contentDescription = contentDescription!!,
                tint = Color(PrimaryBlue.value)
            )
        },
        isError = isError,
        visualTransformation = visualTransformation ?: transformation
    )
}

private fun applyMask(mask: String, text: String): String {
    var index = 0
    val maskedText = StringBuilder()
    for (char in mask) {
        if (index >= text.length) break
        if (char == '#') {
            maskedText.append(text[index])
            index++
        } else {
            maskedText.append(char)
        }
    }
    return maskedText.toString()
}

private fun createOffsetMapping(mask: String): OffsetMapping {
    return object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            var transformedOffset = offset
            var originalOffset = 0

            mask.forEachIndexed { index, char ->
                if (index >= transformedOffset) return@forEachIndexed
                if (char != '#') transformedOffset++
                if (index < offset && char != '#') originalOffset++
            }
            return transformedOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            var originalOffset = offset

            mask.forEachIndexed { index, char ->
                if (index >= originalOffset) return@forEachIndexed
                if (char != '#') originalOffset++
            }
            return originalOffset
        }
    }
}