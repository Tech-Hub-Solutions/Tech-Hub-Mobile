package com.example.techhub.common

import com.example.techhub.domain.RetrofitService

object Constants {
    /* TODO - Alterar o endereço de IP para o endereço do servidor
    * ⚠️ sempre trocar o IP para o IPv4 da sua máquina que aparece quando executa "ipconfig" no terminal
    */
    private const val IP = "10.0.0.103"
    const val BASE_URL = "http://${IP}:8080/api/"
    const val PARAM_TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtdXJpbG9kc2JfMjAxOUBob3RtYWlsLmNvbSIsImlhdCI6MTcxMzkxNDc1MiwiZXhwIjoxNzE3NTE0NzUyfQ.pyiETp6DqH16t2RzYSZL4h5UWIwdLy1Q_do3RbRr7DNUVSOBmCIVYJI9I5bv3XnSosd3fDGA20Ann6u0AAl3NQ"
    const val EMPRESA = "empresa"
    const val FREELANCER = "freelancer"
}