package com.example.techhub.common.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.example.techhub.data.prefdatastore.DataStoreManager
import com.example.techhub.domain.model.updateCurrentUser
import com.example.techhub.presentation.perfil.PerfilActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CurrentLink {
    var appLinkIntent: Intent? = null
    var appLinkData: Uri? = null
    var appLinkId: Int? = null
}

fun verifyCredentials(
    context: Context,
    errorRedirectActivity: Class<*>,
    redirectToOwnProfile: Boolean = true,
    appLinkIntent: Intent? = null,
    appLinkData: Uri? = null,
) {
    val dataStoreManager = DataStoreManager(context = context)
    val dataStore = dataStoreManager.getFromDataStore()

    CoroutineScope(Dispatchers.IO).launch {
        dataStore.collect { value ->
            val token = value.userTokenJwt
            if (appLinkData != null) {
                CurrentLink.appLinkIntent = appLinkIntent
                CurrentLink.appLinkData = appLinkData
                CurrentLink.appLinkId = appLinkData.lastPathSegment?.toInt()
            }

            if (token.isEmpty()) {
                startNewActivity(context, errorRedirectActivity)
            } else {
                updateCurrentUser(value)
                val extras = Bundle()
                if (redirectToOwnProfile) {
                    extras.putInt("id", value.userProfile!!.id!!)
                    startNewActivity(context, PerfilActivity::class.java, extras)
                }
                if (appLinkData != null) {
                    extras.putInt("id", CurrentLink.appLinkId ?: 0)
                    appLinkIntent!!.putExtras(extras)
                    Log.d("CurrentLink", CurrentLink.appLinkIntent.toString())
                    context.startActivity(CurrentLink.appLinkIntent)
                }
            }
        }
    }
}