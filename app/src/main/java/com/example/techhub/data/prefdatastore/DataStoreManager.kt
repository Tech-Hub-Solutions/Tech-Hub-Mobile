package com.example.techhub.data.prefdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.google.gson.Gson
import kotlinx.coroutines.flow.map
import com.example.techhub.domain.model.datastore.DataStoreData

private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(Any::class.java.simpleName)

class DataStoreManager(val context: Context) {
    private val gson = Gson()

    companion object {
        val USER_TOKEN_JWT = stringPreferencesKey("user_token_jwt")
        val URL_PROFILE_IMAGE = stringPreferencesKey("user_url_profile_image")
        val USER_PROFILE = stringPreferencesKey("user_profile")
    }

    suspend fun saveToDataStore(dataStoreData: DataStoreData) {
        context.dataStore.edit {
            it[USER_TOKEN_JWT] = dataStoreData.userTokenJwt
            it[URL_PROFILE_IMAGE] = dataStoreData.urlProfileImage
            it[USER_PROFILE] = gson.toJson(dataStoreData.userProfile)
        }
    }

    fun getFromDataStore() = context.dataStore.data.map {
        DataStoreData(
            userTokenJwt = it[USER_TOKEN_JWT] ?: "",
            urlProfileImage = it[URL_PROFILE_IMAGE] ?: "",
            userProfile = it[USER_PROFILE]?.let { gson.fromJson(it, UsuarioTokenData::class.java) }
        )
    }

    suspend fun clearDataStore() = context.dataStore.edit {
        it.clear()
    }

}

