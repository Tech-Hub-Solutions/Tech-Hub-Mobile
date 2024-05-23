package com.example.techhub.common.composable

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.objects.countryFlagsList
import com.example.techhub.common.utils.UiText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun FlagDropDownMenu(flag: MutableState<String>, context: Context) {

    val countryFlags = countryFlagsList.toList()

    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFFFFF)),
                    value = flag.value,
                    onValueChange = {
                        flag.value = it
                        expanded = true
                    },
                    label = {
                        Text(
                            UiText.StringResource(
                                R.string.label_text_search_country
                            ).asString(context = context)
                        )
                    },
                    placeholder = {
                        Text(
                            UiText.StringResource(
                                R.string.placeholder_text_search_country
                            ).asString(context = context)
                        )
                    },
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
                            imageVector = Icons.Filled.Flag,
                            contentDescription = UiText.StringResource(
                                R.string.description_image_nome
                            ).asString(context = context),
                            tint = Color(PrimaryBlue.value)
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowDropDown,
                                contentDescription = null,
                            )
                        }
                    }
                )
            }

            AnimatedVisibility(visible = expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .shadow(8.dp),
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = 200.dp)
                            .background(Color.White)
                    ) {
                        if (flag.value.isNotEmpty()) {
                            items(
                                countryFlags.filter {
                                    it.name.lowercase()
                                        .contains(flag.value.lowercase()) || it.name.contains("others")
                                }
                            ) {
                                ItemSelecionavel(title = it.name) { title ->
                                    flag.value = title
                                    expanded = false
                                }
                            }
                        } else {
                            items(
                                countryFlags.sortedBy { it.name }
                            ) {
                                ItemSelecionavel(title = it.name) { title ->
                                    flag.value = title
                                    expanded = false
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

