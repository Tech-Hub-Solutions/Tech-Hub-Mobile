package com.example.techhub.common.composable

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FloatingActionButtonScroll(
    isScrolled: Boolean,
    scrollState: ScrollState,
    scope: CoroutineScope
) {
    if (isScrolled) {
        FloatingActionButton(
            onClick = {
                scope.launch {
                    scrollState.animateScrollTo(0)
                }
            },
            containerColor = Color(PrimaryBlue.value),
            shape = FloatingActionButtonDefaults.smallShape,
            elevation = FloatingActionButtonDefaults.elevation(10.dp),
            contentColor = Color.White,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                Icons.Filled.ArrowUpward,
                "@string/btn_description_up_scroll_to_top",
            )
        }
    }
}