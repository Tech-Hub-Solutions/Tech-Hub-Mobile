package com.example.techhub.presentation.perfil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.techhub.composable.SetBarColor
import com.example.techhub.presentation.ui.theme.TechHubTheme
import com.example.techhub.presentation.perfil.composables.PerfilView
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import com.example.techhub.common.utils.verifyCredentials
import com.example.techhub.presentation.login.LoginActivity

class PerfilActivity : ComponentActivity() {
    var model = PerfilViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data


        setContent {
            TechHubTheme {
                SetBarColor(color = Color.White)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val extras = intent.extras
                    val id = extras?.getInt("id")

                    LaunchedEffect(Unit) {
                        if (id == null || id == 0) {
                            Log.d("PerfilActivityy", appLinkData.toString())
                            verifyCredentials(
                                this@PerfilActivity,
                                errorRedirectActivity = LoginActivity::class.java,
                                redirectToOwnProfile = false,
                                appLinkIntent = appLinkIntent,
                                appLinkData = appLinkData
                            )
                        }
                    }

                    PerfilView(id = id, model)
                }
            }
        }
    }
}