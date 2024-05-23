package com.example.techhub.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.techhub.presentation.index.IndexActivity
import com.example.techhub.presentation.perfil.PerfilActivity
import android.content.Intent
import android.net.Uri
import com.example.techhub.common.utils.verifyCredentials

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        verifyCredentials(
            this@MainActivity,
            errorRedirectActivity = IndexActivity::class.java,
            redirectToOwnProfile = true
        )
        super.onCreate(savedInstanceState)
        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data
    }
}