package com.example.techhub.domain

import android.content.Context
import com.example.techhub.common.utils.showToastError
import com.example.techhub.common.utils.showWelcomeToastWithName
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import com.example.techhub.presentation.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun verifyUser(
    usuarioSimpleVerifyData: UsuarioVerifyData,
    context: Context,
    toastErrorMessage: String,
) {
    val userVerifyData = UsuarioVerifyData(
        email = usuarioSimpleVerifyData.email,
        senha = usuarioSimpleVerifyData.senha,
        code = usuarioSimpleVerifyData.code
    )
    val usuarioService = RetrofitService.getUsuarioService()

    usuarioService.verifyUser(userVerifyData).enqueue(object : Callback<UsuarioTokenData> {
        override fun onResponse(
            call: Call<UsuarioTokenData>,
            response: Response<UsuarioTokenData>
        ) {
            if (response.isSuccessful) {
                showWelcomeToastWithName(
                    context = context,
                    fullName = response.body()?.nome!!,
                )

                val token = response.body()?.token
                // TODO - Salvar token e email do usuário no Data Store
                // TODO - Fazer redirect para a tela de perfil do usuário
                startNewActivity(
                    activity = LoginActivity::class.java,
                    context = context
                )
            } else {
                showToastError(context, toastErrorMessage)
            }
        }

        override fun onFailure(call: Call<UsuarioTokenData>, t: Throwable) {
            showToastError(context, toastErrorMessage)
        }
    })
}