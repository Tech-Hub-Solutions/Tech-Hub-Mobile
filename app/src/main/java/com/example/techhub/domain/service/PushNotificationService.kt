package com.example.techhub.domain.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNoficationService: FirebaseMessagingService(){

    val tag:String = "FCMToken"
    override fun onNewToken(token: String) {
        Log.d(tag, "Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(tag, "Message data payload: ${remoteMessage.data}")

        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(tag, "Message Notification Body: ${it.body}")
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

}