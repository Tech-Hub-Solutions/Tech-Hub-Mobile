package com.example.techhub.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.data.prefdatastore.DataStoreManager
import com.example.techhub.domain.RetrofitService
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
            dataStore.collect {
                val tokenDataStore = it.userTokenJwt
                Log.d("INITIAL TOKEN VALUE", tokenDataStore)

                if (tokenDataStore.isEmpty()) {
                    startNewActivity(context, IndexActivity::class.java)
                } else {
                    RetrofitService.updateTokenJwt(tokenDataStore)
                    startNewActivity(context, PerfilActivity::class.java)
                }
            }
        }
        super.onCreate(savedInstanceState)

    }
}