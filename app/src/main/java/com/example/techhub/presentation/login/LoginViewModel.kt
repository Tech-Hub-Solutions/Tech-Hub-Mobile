package com.example.techhub.presentation.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.techhub.R
import com.example.techhub.common.utils.CurrentLink
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.redirectToPerfilUsuario
import com.example.techhub.common.utils.showToastError
import com.example.techhub.common.utils.showWelcomeToastWithName
import com.example.techhub.domain.service.RetrofitService
import com.example.techhub.domain.model.updateCurrentUser
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val authApi = RetrofitService.getAuthService()

    fun loginUser(
        user: UsuarioLoginData,
        context: Context,
        onAuthSucess: (UsuarioLoginData) -> Unit
    ) {
        val toastErrorMessage =
            UiText.StringResource(R.string.toast_text_error_login).asString(context = context)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = authApi.loginUser(user)

                if (response.isSuccessful) {
                    if (response.body()?.isUsing2FA!!) {
                        onAuthSucess(user)
                    } else {
                        updateCurrentUser(
                            context = context,
                            usuarioTokenData = response.body()!!,
                            email = user.email!!
                        )

                        if (CurrentLink.appLinkData == null) {
                            val extras = Bundle()
                            extras.putInt("id", response.body()?.id!!)
                            redirectToPerfilUsuario(
                                context = context,
                                fullName = response.body()?.nome!!,
                                extras = extras
                            )
                        } else {
                            showWelcomeToastWithName(
                                context = context,
                                fullName = response.body()?.nome!!
                            )
                            val intent = CurrentLink.appLinkIntent
                            val extras = Bundle()
                            extras.putInt("id", CurrentLink.appLinkId ?: 0)
                            context.startActivity(intent)
                        }
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