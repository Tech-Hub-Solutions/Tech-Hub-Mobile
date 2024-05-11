package com.example.techhub.presentation.index.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.techhub.R
import com.example.techhub.common.composable.CenteredImageSection
import com.example.techhub.common.utils.UiText

@Composable
fun IndexView() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenteredImageSection(
            imagePath = R.mipmap.logo,
            contentDescription = UiText.StringResource(
                R.string.description_image_logo
            ).asString(context = context),
            width = 110,
            height = 20,
        )

        Spacer(modifier = Modifier.height(25.dp))

        CenteredImageSection(
            imagePath = R.mipmap.index_image,
            contentDescription = UiText.StringResource(
                R.string.description_image_index
            ).asString(context = context),
            width = 252,
            height = 300,
        )

        CustomTextSection()

        ButtonsSection(context = context)
    }
}