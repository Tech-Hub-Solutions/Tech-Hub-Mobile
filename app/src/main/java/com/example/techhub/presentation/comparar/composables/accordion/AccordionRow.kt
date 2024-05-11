package com.example.techhub.presentation.comparar.composables.accordion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.domain.model.accordion.AccordionModel
import com.example.techhub.presentation.ui.theme.GrayAccordionRowText

@Composable
fun AccordionRow(
    model: AccordionModel.Row
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (model.user1) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color(0xff47AF64),
                modifier = Modifier.size(40.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color(0xffFF7474),
                modifier = Modifier.size(40.dp)
            )
        }

        Surface {
            Text(
                model.tecnologia,
                Modifier
                    .weight(1f)
                    .background(Color.White),
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                color = GrayAccordionRowText,
            )
        }

        if (model.user2) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color(0xff47AF64),
                modifier = Modifier.size(40.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color(0xffFF7474),
                modifier = Modifier.size(40.dp)
            )
        }
    }
}