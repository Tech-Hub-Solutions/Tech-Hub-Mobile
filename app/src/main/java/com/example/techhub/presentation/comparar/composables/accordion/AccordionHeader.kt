package com.example.techhub.presentation.comparar.composables.accordion

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.utils.UiText

@Composable
fun AccordionHeader(
    title: String = "Header",
    isExpanded: Boolean = false,
    onTapped: () -> Unit = {},
    context: Context
) {
    val degrees = if (isExpanded) 180f else 0f

    Surface(
        color = Color.White,
        shadowElevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .clickable { onTapped() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                title,
                Modifier.weight(1f),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                color = Color(0xFF0F9EEA)
            )
            Surface(shape = CircleShape, color = Color(0xFF0F9EEA)) {
                Icon(
                    imageVector = Icons.Outlined.ArrowDropDown,
                    contentDescription = UiText.StringResource(
                        R.string.description_image_expand
                    ).asString(context = context),
                    modifier = Modifier.rotate(degrees),
                    tint = Color.White
                )
            }
        }
    }
}