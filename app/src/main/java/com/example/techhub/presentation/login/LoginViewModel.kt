package com.example.techhub.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.techhub.common.Constants
import com.example.techhub.common.Resource
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.example.techhub.domain.use_case.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    /*
    * o SavedStateHandle é usado para salvar o estado da tela
    * ele armazena os dados em um Bundle, que é um mapa de chave-valor
    * e não precisamos passar o Bundle manualmente
    * */
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    /*
    * deixamos o _state privado p/ não poder ser alterado de fora
    * O state que é público usa um Imutável State, assim
    * não é possível alterar o valor de fora da classe
    * */
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    init {
        savedStateHandle.get<UsuarioTokenData>(Constants.PARAM_TOKEN)?.let { token ->
            _state.value = LoginState()
        }
    }

    suspend fun login(usuario: UsuarioLoginData) {
        loginUseCase(usuario).collect { result ->
            when (result) {
                is Resource.Success<UsuarioTokenData> -> {
                    _state.value = LoginState(usuarioToken = result.data)
                }

                is Resource.Error<UsuarioTokenData> -> {
                    _state.value = LoginState(
                        error = result.message ?: "Ocorreu um erro inesperado"
                    )
                }

                is Resource.Loading<UsuarioTokenData> -> {
                    _state.value = LoginState(isLoading = true)
                }
            }
        }
    }
}