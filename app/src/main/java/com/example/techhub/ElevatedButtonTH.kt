package com.example.techhub

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.ui.theme.PrimaryBlue

@Composable
fun ElevatedButtonTH(
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color,
    textColor: Color = Color.White
) {
    ElevatedButton(
        onClick = { onClick() },
        modifier = Modifier
            .padding(10.dp)
            .width(350.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
        ),
        border = BorderStroke(
            1.dp, Color(PrimaryBlue.value)
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight(300))
    }
}