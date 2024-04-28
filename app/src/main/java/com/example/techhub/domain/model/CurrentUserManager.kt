package com.example.techhub.domain.model

import android.content.Context
import android.util.Log
import com.example.techhub.common.enums.UsuarioFuncao
import com.example.techhub.data.prefdatastore.DataStoreManager
import com.example.techhub.domain.model.datastore.DataStoreData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CurrentUser {
    var userTokenJwt: String? = ""
    var urlProfileImage: String? = ""
    var userProfile: UsuarioTokenData? = null
    var isEmpresa: Boolean = false
}

fun updateCurrentUser(context: Context, usuarioTokenData: UsuarioTokenData) {
    val dataStoreManager = DataStoreManager(context)

    CoroutineScope(Dispatchers.Main).launch {
        val data = DataStoreData(
            userTokenJwt = usuarioTokenData.token!!,
            urlProfileImage = usuarioTokenData.urlFotoPerfil ?: "",
            userProfile = usuarioTokenData,
            userFuncao = usuarioTokenData.funcao == UsuarioFuncao.EMPRESA
        )
        dataStoreManager.saveToDataStore(data)
        updateCurrentUser(data)
    }
}

fun updateCurrentUser(dataStoreData: DataStoreData) {
    Log.d("UpdateCurrentUser", dataStoreData.userTokenJwt)
    CurrentUser.userTokenJwt = dataStoreData.userTokenJwt
    CurrentUser.urlProfileImage = dataStoreData.urlProfileImage
    CurrentUser.userProfile = dataStoreData.userProfile
    CurrentUser.isEmpresa = dataStoreData.userFuncao
}
