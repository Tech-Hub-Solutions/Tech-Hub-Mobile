package com.example.techhub.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.techhub.common.countryFlagsList
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlagDropDownMenu() {

    val countryFlags = countryFlagsList.toList()
    var flag by remember { mutableStateOf("") }
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
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(Color(0xFFFFFFFF))
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(4.dp),
                        ),
                    value = flag,
                    onValueChange = {
                        flag = it
                        expanded = true
                    },
                    label = { Text("Digite para pesquisar") },
                    placeholder = { Text("Digite o país") },
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
                            contentDescription = "campo para o nome",
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
                                contentDescription = "ArrowDropDown",
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
                        if (flag.isNotEmpty()) {
                            items(
                                countryFlags.filter {
                                    it.name.lowercase()
                                        .contains(flag.lowercase()) || it.name.contains("others")
                                }
                            ) {
                                FlagItems(title = it.name) { title ->
                                    flag = title
                                    expanded = false
                                }
                            }
                        } else {
                            items(
                                countryFlags.sortedBy { it.name }
                            ) {
                                FlagItems(title = it.name) { title ->
                                    flag = title
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

@Composable
fun FlagItems(
    title: String,
    onSelect: (String) -> Unit
) {
    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelect(title) }
                .padding(10.dp)
        ) {
            Text(text = title, fontSize = 16.sp)
        }

        Divider(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            color = Color.Gray.copy(alpha = 0.2f)
        )
    }

}

//@Composable
//fun FlagDropDownMenu(
//    suggestions: List<CountryFlags>,
//    onItemSelected: (String) -> Unit
//) {
//    var text by remember { mutableStateOf("") }
//    var isSuggestionsVisible by remember { mutableStateOf(false) }
//    val filteredSuggestions by remember(suggestions, text) {
//        derivedStateOf {
//            suggestions.filter {
//                it.name.contains(text, ignoreCase = true)
//            }
//        }
//    }
//
//    if (isSuggestionsVisible && filteredSuggestions.isEmpty()) {
//        isSuggestionsVisible = false
//    }
//
//    Column {
//
//        TextField(
//            value = text,
//            onValueChange = {
//                text = it
//                isSuggestionsVisible = false
//            },
//            label = { Text("Digite para pesquisar") },
//            placeholder = { Text("Digite o país") },
//            textStyle = LocalTextStyle.current.copy(
//                color = Color.Black,
//                fontSize = 16.sp
//            ),
//            colors = TextFieldDefaults.colors(
//                cursorColor = Color(PrimaryBlue.value),
//                errorCursorColor = Color(PrimaryBlue.value),
//                focusedContainerColor = Color.Transparent,
//                unfocusedContainerColor = Color.Transparent,
//                errorContainerColor = Color.Transparent,
//                errorSupportingTextColor = Color.Red.copy(alpha = 0.6f),
//            ),
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Filled.Flag,
//                    contentDescription = "campo para o nome",
//                    tint = Color(PrimaryBlue.value)
//                )
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            singleLine = true,
//        )
//
//        LaunchedEffect(text) {
//            delay(500) // Adjust delay as needed (in milliseconds)
//            if (text.isNotBlank()) {
//                isSuggestionsVisible = true
//                val filteredSuggestions =
//                    suggestions.filter { it.name.contains(text, ignoreCase = true) }
//                if (filteredSuggestions.isNotEmpty()) {
//                    filteredSuggestions.forEach { suggestion ->
//                        Log.d("Autocomplete", "Suggestion: $suggestion")
//                    }
//                } else {
//                    Log.d("Autocomplete", "No suggestions found")
//                }
//            }
//        }
//
//        if (isSuggestionsVisible && text.isNotEmpty()) {
//            DropdownMenu(
//                modifier = Modifier
//                    .fillMaxWidth(0.9f)
//                    .background(Color.White),
//                expanded = true,
//                onDismissRequest = { isSuggestionsVisible = false }
//            ) {
//                filteredSuggestions.take(5).forEach { suggestion ->
//                    DropdownMenuItem(
//                        text = {
//                            Text(
//                                text = suggestion.name,
//                                maxLines = 1,
//                            )
//                        },
//                        onClick = {
//                            text = suggestion.name
//                            onItemSelected(suggestion.name)
//                            isSuggestionsVisible = false
//                        }
//                    )
//                }
//            }
//        }
//    }
//}

