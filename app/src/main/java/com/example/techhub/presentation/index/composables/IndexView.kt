package com.example.techhub.presentation.index.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.techhub.R
import com.example.techhub.common.composable.CenteredImageSection
import com.example.techhub.presentation.ui.theme.dimensions

@Composable
fun IndexView() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenteredImageSection(
            imagePath = R.mipmap.logo,
            contentDescription = "@string/description_image_logo",
            width = MaterialTheme.dimensions.logoWidth,
            height = MaterialTheme.dimensions.logoHeight,
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.medium1))

        CenteredImageSection(
            imagePath = R.mipmap.index_image,
            contentDescription = "@string/description_image_index",
            width = MaterialTheme.dimensions.centeredImageWidthLarge,
            height = MaterialTheme.dimensions.centeredImageHeightLarge,
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.medium3))

        CustomTextSection()

        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.medium3))

        ButtonsSection(context = context)
    }
}