package com.example.techhub.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.presentation.ui.theme.GrayText

@Composable
fun ItemSelecionavel(
    title: String,
    color: Color = GrayText,
    underlineColor: Color = GrayText,
    underlineHeight: Int = 0,
    onSelect: (String) -> Unit
) {
    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelect(title) }
                .padding(10.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                color = color,
                modifier = Modifier.drawBehind {
                    val strokeWidthPx = underlineHeight.dp.toPx()
                    val verticalOffset = size.height - 2.sp.toPx()
                    drawLine(
                        color = underlineColor,
                        strokeWidth = strokeWidthPx,
                        start = Offset(0f, verticalOffset),
                        end = Offset(size.width, verticalOffset)
                    )
                },
            )
        }

        Divider(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            color = Color.Gray.copy(alpha = 0.2f)
        )
    }
}