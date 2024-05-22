package com.example.techhub.common.composable

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
    fun FloatingActionButtonScrollLazyColumn(
    isScrolled: Boolean,
    listState: LazyListState,
    scope: CoroutineScope,
    context: Context
) {
    if (isScrolled) {
        Log.d("FLOATIN ACTION BUTTON","TA NO IF")
        FloatingActionButton(
            onClick = {
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            },
            containerColor = Color(PrimaryBlue.value),
            shape = FloatingActionButtonDefaults.smallShape,
            elevation = FloatingActionButtonDefaults.elevation(10.dp),
            contentColor = Color.White,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowUpward,
                contentDescription = UiText.StringResource(
                    R.string.btn_description_up_scroll_to_top
                ).asString(context = context)
            )
        }
    }
}