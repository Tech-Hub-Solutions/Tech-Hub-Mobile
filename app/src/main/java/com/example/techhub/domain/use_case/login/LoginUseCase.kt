package com.example.techhub.domain.use_case.login

import com.example.techhub.common.Resource
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.domain.model.usuario.UsuarioTokenData
import com.example.techhub.domain.repository.UsuarioRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UsuarioRepository
) {
    operator fun invoke(usuario: UsuarioLoginData): Flow<Resource<UsuarioTokenData>> = flow {
        try {
            emit(Resource.Loading())
            val usuario = repository.login(usuario = usuario)

            emit(Resource.Success(usuario))
        } catch (error: HttpException) {
            emit(Resource.Error(error.localizedMessage ?: "An unexpected error occurred"))
        } catch (error: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}