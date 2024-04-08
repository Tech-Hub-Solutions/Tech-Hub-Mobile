package com.example.techhub

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.composable.CenteredImageSection
import com.example.techhub.composable.ElevatedButtonTH
import com.example.techhub.composable.SetBarColor
import com.example.techhub.composable.startNewActivity
import com.example.techhub.service.Navigation
import com.example.techhub.ui.theme.GrayText
import com.example.techhub.ui.theme.PrimaryBlue
import com.example.techhub.ui.theme.TechHubTheme
import com.example.techhub.view.CadastroActivity
import com.example.techhub.view.LoginActivity

var wasToastShowed: Boolean = false

class IndexActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechHubTheme {
                SetBarColor(color = Color.White)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
            showToast(context = this)

            IndexContent()
        }
    }
}

fun showToast(context: Context) {
    if (!wasToastShowed) {
        val toast: Toast = Toast.makeText(context, "Seja bem-vindo(a)!", Toast.LENGTH_SHORT)
        toast.show()

        wasToastShowed = true
    }
}

@Composable
fun IndexContent() {
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
            contentDescription = "@string/description_image_logo",
            width = 110,
            height = 20,
        )

        Spacer(modifier = Modifier.height(25.dp))

        CenteredImageSection(
            imagePath = R.mipmap.index_image,
            contentDescription = "@string/description_image_index",
            width = 252,
            height = 300,
        )

        CustomTextSection()

        ButtonsSection(context = context)
    }
}

@Composable
fun CustomTextSection() {
    Column(
        modifier = Modifier
            .padding(vertical = 40.dp, horizontal = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 32.sp
                    )
                ) {
                    append("O ponto de encontro dos ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color(PrimaryBlue.value),
                        fontWeight = FontWeight.Medium,
                        fontSize = 32.sp
                    )
                ) {
                    append("talentos")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 32.sp
                    )
                ) {
                    append("!")
                }
            },
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = "Unindo talento e necessidade.",
            color = Color(GrayText.value),
            fontWeight = FontWeight.Thin,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ButtonsSection(context: Context) {
    Column {
        ElevatedButtonTH(
            onClick = { startNewActivity(context = context, LoginActivity::class.java) },
            text = "Entrar",
            backgroundColor = Color(PrimaryBlue.value),
            width = (350),
            height = (60),
        )

        ElevatedButtonTH(
            onClick = { startNewActivity(context = context, CadastroActivity::class.java) },
            text = "Cadastrar",
            backgroundColor = Color(Color.White.value),
            textColor = Color(PrimaryBlue.value),
            width = (350),
            height = (60),
        )
    }
}

