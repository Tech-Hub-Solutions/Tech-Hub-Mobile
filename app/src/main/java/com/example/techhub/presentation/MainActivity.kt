package com.example.techhub.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.data.prefdatastore.DataStoreManager
import com.example.techhub.domain.model.updateCurrentUser
import com.example.techhub.presentation.index.IndexActivity
import com.example.techhub.presentation.perfil.PerfilActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val context = this@MainActivity
        val dataStoreManager = DataStoreManager(context = context)
        val dataStore = dataStoreManager.getFromDataStore()

        CoroutineScope(Dispatchers.IO).launch {
            dataStore.collect { value ->
                val token = value.userTokenJwt

                if (token.isEmpty()) {
                    startNewActivity(context, IndexActivity::class.java)
                } else {
                    updateCurrentUser(value)
                    val extras = Bundle()
                    extras.putInt("id", value.userProfile?.id!!)
                    startNewActivity(context, PerfilActivity::class.java, extras)
                }
            }
        }
        super.onCreate(savedInstanceState)
    }
}