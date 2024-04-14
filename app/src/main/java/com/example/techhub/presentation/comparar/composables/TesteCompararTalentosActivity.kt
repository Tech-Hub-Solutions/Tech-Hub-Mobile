package com.example.techhub.presentation.comparar.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.ui.theme.TechHubTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class TesteCompararTalentosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var scaffoldState = rememberBottomSheetScaffoldState()
                    val scope = rememberCoroutineScope()

                    BottomSheetScaffold(
                        modifier = Modifier
                            .fillMaxHeight(.98f)
                            .verticalScroll(rememberScrollState()),
                        scaffoldState = scaffoldState,
                        sheetContent = {
                            CompararTalentosView(scaffoldState, scope)
                        },
                        sheetPeekHeight = 70.dp,
                        sheetDragHandle = {
                            TextButton(
                                onClick = {
                                    scope.launch {
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }, modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(70.dp)
                                    .background(color = Color(0xFF0F9EEA))
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(0.8f),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Comparar Selecionados", color = Color.White,
                                        fontSize = 24.sp
                                    )
                                    Icon(
                                        imageVector = Icons.Filled.CompareArrows,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(40.dp)
                                    )
                                }
                            }
                        },
                        sheetShape = MaterialTheme.shapes.extraSmall
                    ) {
                        FavoritosView()
                    }
                }
            }
        }
    }
}

@Composable
fun FavoritosView() {
    Text(text = "Resende é viado")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TechHubTheme {
        FavoritosView()
    }
}