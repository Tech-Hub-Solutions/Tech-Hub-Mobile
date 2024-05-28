package com.example.techhub.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.techhub.presentation.index.IndexActivity
import com.example.techhub.presentation.perfil.PerfilActivity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import com.example.techhub.common.utils.verifyCredentials
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

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

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener{ task ->
                if (!task.isSuccessful){
                    Log.d("FCM Notify", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Obtendo novo token de registro
                val FCMtoken: String? = task.result
                Log.d("FCM Token", FCMtoken, task.exception)
                Toast.makeText(this, FCMtoken, Toast.LENGTH_SHORT).show()
            })
    }


}