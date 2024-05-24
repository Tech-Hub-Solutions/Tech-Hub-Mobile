package com.example.techhub.domain.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNoficationService: FirebaseMessagingService(){
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.v("CloudMassage","From: ${message.from}")

        if(message.data.isNotEmpty()){
            Log.v("CloudMassage","Message data payload: ${message.data}")
        }

        message.data?.let {
            Log.v("CloudMassage","Message Notification Body ${it["body"]}")
        }

        if(message.notification != null){
            Log.v("CloudMassage","Message Notification ${message.notification}")
            Log.v("CloudMassage","Message Notification Title ${message.notification!!.title}")
            Log.v("CloudMassage","Message Notification Body ${message.notification!!.body}")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}