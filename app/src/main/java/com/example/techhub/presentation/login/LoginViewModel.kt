package com.example.techhub.presentation.login

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.techhub.common.utils.redirectToPerfilUsuario
import com.example.techhub.common.utils.showToastError
import com.example.techhub.domain.RetrofitService
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    private val toastErrorMessage = "Ops! Algo deu errado.\n Tente novamente."

    private val apiUsuario = RetrofitService.getUsuarioService()

    fun loginUser(
        user: UsuarioLoginData,
        context: Context,
        onAuthSucess: (UsuarioLoginData) -> Unit
    ) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiUsuario.loginUser(user)

                if (response.isSuccessful) {
                    if (response.body()?.isUsing2FA!!) {
                        onAuthSucess(user)
                    } else {


                        RetrofitService.updateTokenJwt(
                            context = context,
                            usuarioTokenData = response.body()!!
                        )

                        redirectToPerfilUsuario(
                            context = context,
                            fullName = response.body()?.nome!!
                        )
                    }
                } else {
                    (context as Activity).runOnUiThread {
                        showToastError(context = context, message = toastErrorMessage)
                    }
                }
            } catch (error: Exception) {
                Log.e("LOGIN_VIEW_MODEL", "ERROR: ${error.message}")

                (context as Activity).runOnUiThread {
                    showToastError(context = context, message = toastErrorMessage)
                }
            }
        }
    }
}