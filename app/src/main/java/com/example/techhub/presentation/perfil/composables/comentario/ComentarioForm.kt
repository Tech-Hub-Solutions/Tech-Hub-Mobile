package com.example.techhub.presentation.perfil.composables.comentario

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.techhub.common.composable.StarBarWithVariableRating
import com.example.techhub.common.composable.StarRatingBarFixed
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ComentarioForm(
    urlFoto: String,
    filledText: MutableState<String>,
    rating: MutableState<Double>
) {
    Column(modifier = Modifier
        .padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (urlFoto.isNullOrBlank()) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "@string/btn_description_profile",
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
                    contentDescription = "Imagem do avaliador",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(45.dp)
                        .clip(CircleShape)
                )
            }

            Column(modifier = Modifier) {
                Text(
                    text = "Você",
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
            label = { Text("Comentário") },
            placeholder = { Text("adicionar comentário") },
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