package com.example.techhub.common.composable

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.presentation.ui.theme.GrayButtonText
import com.example.techhub.presentation.ui.theme.GraySwitchUnchecked
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun Switch2FALeft(
    onValueChanged: (Boolean) -> Unit,
    initialValue: Boolean = false,
    context: Context
) {
    var checked by remember { mutableStateOf(initialValue) }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
                onValueChanged(checked)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = PrimaryBlue,
                checkedTrackColor = PrimaryBlue.copy(alpha = 0.5f),
                uncheckedThumbColor = GrayButtonText.copy(alpha = 0.5f),
                uncheckedTrackColor = GraySwitchUnchecked,
                uncheckedBorderColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        Text(
            text = UiText.StringResource(
                R.string.text_authenticator
            ).asString(context = context),
            color = Color(GrayText.value),
            fontWeight = FontWeight.Thin,
            fontSize = 14.sp
        )
    }
}