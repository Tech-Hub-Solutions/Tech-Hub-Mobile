package com.example.techhub.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AlertDialogSample(
    title: String,
    text: String,
    ) {
    val (openDialog, setOpenDialog) = remember { mutableStateOf(false) }

    Column {
        setOpenDialog(true)

        if (openDialog) {
            AlertDialog(
                onDismissRequest = {
                    setOpenDialog(false)
                },
                title = {
                    Text(text = title)
                },
                text = {
                    Text(text = text)
                },
                confirmButton = {
                    Button(
                        onClick = {
                            setOpenDialog(false)
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            setOpenDialog(false)
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}