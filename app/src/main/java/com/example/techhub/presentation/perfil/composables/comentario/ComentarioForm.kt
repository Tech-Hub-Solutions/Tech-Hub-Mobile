package com.example.techhub.presentation.perfil.composables.comentario

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.techhub.R
import com.example.techhub.common.composable.StarBarWithVariableRating
import com.example.techhub.common.utils.UiText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun ComentarioForm(
    urlFoto: String,
    filledText: MutableState<String>,
    rating: MutableState<Double>
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (urlFoto.isNullOrBlank()) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = UiText.StringResource(
                        R.string.btn_description_profile
                    ).asString(context = context),
                    tint = PrimaryBlue,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE4E4E4))
                        .padding(2.dp)
                        .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(urlFoto)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = UiText.StringResource(
                        R.string.description_image_evaluator
                    ).asString(context = context),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(45.dp)
                        .clip(CircleShape)
                )
            }

            Column(modifier = Modifier) {
                Text(
                    text = UiText.StringResource(
                        R.string.text_you
                    ).asString(context = context),
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(0.dp),
                )

                StarBarWithVariableRating(
                    maxStars = 5,
                    rating = rating,
                    onRatingChanged = {
                        rating.value = it
                    },
                    size = 8
                )
            }
        }

        OutlinedTextField(
            value = filledText.value,
            onValueChange = {
                filledText.value = it
            },
            label = {
                Text(
                    UiText.StringResource(
                        R.string.title_comment
                    ).asString(context = context)
                )
            },
            placeholder = {
                Text(
                    UiText.StringResource(
                        R.string.text_add_comment
                    ).asString(context = context)
                )
            },
            textStyle = LocalTextStyle.current.copy(
                color = Color.Black,
                fontSize = 16.sp
            ),
            singleLine = false,
            minLines = 1,
            maxLines = 6,
            colors = TextFieldDefaults.colors(
                cursorColor = Color(PrimaryBlue.value),
                errorCursorColor = Color(PrimaryBlue.value),
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                errorSupportingTextColor = Color.Red.copy(alpha = 0.6f),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Go
            ),
        )
    }
}