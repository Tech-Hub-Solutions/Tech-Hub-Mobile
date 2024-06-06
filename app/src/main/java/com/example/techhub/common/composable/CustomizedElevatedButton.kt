package com.example.techhub.common.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.presentation.ui.theme.GrayButtonText
import com.example.techhub.presentation.ui.theme.GrayText

@Composable
fun CustomizedElevatedButton(
    onClick: () -> Unit,
    horizontalPadding: Int,
    verticalPadding: Int,
    defaultElevation: Int,
    pressedElevation: Int,
    containerColor: Color,
    contentColor: Color,
    shape: Shape,
    horizontalArrangement: Arrangement.Horizontal,
    text: String,
    fontSize: Int,
    fontWeight: FontWeight,
    contentDescription: String,
    isLoading: Boolean
) {
    ElevatedButton(
        onClick = { onClick() },
        modifier = Modifier
            .padding(horizontal = horizontalPadding.dp, vertical = verticalPadding.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = defaultElevation.dp,
            pressedElevation = pressedElevation.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        shape = shape,
    ) {
        Row (
            horizontalArrangement = horizontalArrangement) {
            Text(text = text, fontSize = fontSize.sp, fontWeight = fontWeight)
            if (isLoading) {
                CircularProgressIndicatorTH(size = 22.0, color= Color(GrayText.value))
            } else {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    modifier = Modifier.width(24.dp),
                    contentDescription = contentDescription,
                    tint = Color(0xFF505050),
                )
            }
        }

    }
}